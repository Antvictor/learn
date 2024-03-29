package antvictor.study.utils;

import com.microsoft.schemas.office.office.CTLock;
import com.microsoft.schemas.office.office.STConnectType;
import com.microsoft.schemas.vml.*;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.impl.values.XmlValueOutOfRangeException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.springframework.util.CollectionUtils;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MyXWPFHeaderFooterPolicy {
    public static final STHdrFtr.Enum DEFAULT;
    public static final STHdrFtr.Enum EVEN;
    public static final STHdrFtr.Enum FIRST;
    private XWPFDocument doc;
    private List<XWPFHeader> firstPageHeader = new CopyOnWriteArrayList<>();
    private XWPFFooter firstPageFooter;
    private List<XWPFHeader> evenPageHeader = new CopyOnWriteArrayList<>();
    private XWPFFooter evenPageFooter;

    private List<XWPFHeader> defaultHeader = new CopyOnWriteArrayList<>();

    private XWPFFooter defaultFooter;

    private int height = 12;
    private int size = 50;
    private int width = 415;

    public MyXWPFHeaderFooterPolicy(XWPFDocument doc) {
        this(doc, (CTSectPr)null);
    }

    public MyXWPFHeaderFooterPolicy(XWPFDocument doc, CTSectPr sectPr) {
        if (sectPr == null) {
            CTBody ctBody = doc.getDocument().getBody();
            sectPr = ctBody.isSetSectPr() ? ctBody.getSectPr() : ctBody.addNewSectPr();
        }

        this.doc = doc;

        CTHdrFtrRef ref;
        POIXMLDocumentPart relatedPart;
        STHdrFtr.Enum type;
        int i;

        for(i = 0; i < sectPr.sizeOfHeaderReferenceArray(); ++i) {
            ref = sectPr.getHeaderReferenceArray(i);
            relatedPart = doc.getRelationById(ref.getId());
            System.out.println(ref.getId());
            XWPFHeader hdr = null;
            if (relatedPart != null && relatedPart instanceof XWPFHeader) {
                hdr = (XWPFHeader)relatedPart;
            }

            try {
                type = ref.getType();
            } catch (XmlValueOutOfRangeException var10) {
                type = STHdrFtr.DEFAULT;
            }

            this.assignHeader(hdr, type);
        }

        for(i = 0; i < sectPr.sizeOfFooterReferenceArray(); ++i) {
            ref = sectPr.getFooterReferenceArray(i);
            relatedPart = doc.getRelationById(ref.getId());
            XWPFFooter ftr = null;
            if (relatedPart != null && relatedPart instanceof XWPFFooter) {
                ftr = (XWPFFooter)relatedPart;
            }

            try {
                type = ref.getType();
            } catch (XmlValueOutOfRangeException var9) {
                type = STHdrFtr.DEFAULT;
            }

            this.assignFooter(ftr, type);
        }

        System.out.println(doc.getHeaderList().size());
        if (doc.getHeaderList().size() > 0) {
            // 有页眉就不用装作没有
            for (int j = 0; j < doc.getHeaderList().size(); j++) {
                XWPFHeader xwpfHeader = doc.getHeaderArray(j);
                if (xwpfHeader != null) {
                    type = STHdrFtr.DEFAULT;
                    this.assignHeader(xwpfHeader, type);
                }
            }
        }
    }

    private void assignFooter(XWPFFooter ftr, STHdrFtr.Enum type) {
        if (type == STHdrFtr.FIRST) {
            this.firstPageFooter = ftr;
        } else if (type == STHdrFtr.EVEN) {
            this.evenPageFooter = ftr;
        } else {
            this.defaultFooter = ftr;
        }

    }

    private void assignHeader(XWPFHeader hdr, STHdrFtr.Enum type) {
        if (type == STHdrFtr.FIRST) {
            this.firstPageHeader.add(hdr);
        } else if (type == STHdrFtr.EVEN) {
            this.evenPageHeader.add(hdr);
        } else {
            this.defaultHeader.add(hdr);
        }
        System.out.println("header-----" + hdr);
        System.out.println("type-----" + type);

    }

    public void createHeader(STHdrFtr.Enum type) {
        this.createHeader(type, (XWPFParagraph[])null);
    }

    public void createHeader(STHdrFtr.Enum type, XWPFParagraph[] pars) {
        List<XWPFHeader> headeres = this.getHeader(type);
        if (CollectionUtils.isEmpty(headeres)) {
            HdrDocument hdrDoc = HdrDocument.Factory.newInstance();
            XWPFRelation relation = XWPFRelation.HEADER;
            int i = this.getRelationIndex(relation);
            XWPFHeader wrapper = (XWPFHeader)this.doc.createRelationship(relation, XWPFFactory.getInstance(), i);
            wrapper.setXWPFDocument(this.doc);
            String pStyle = "Header";
            CTHdrFtr hdr = this.buildHdr(type, pStyle, wrapper, pars);
            wrapper.setHeaderFooter(hdr);
            hdrDoc.setHdr(hdr);
            this.assignHeader(wrapper, type);
            //header = wrapper;

        }else {

            //header已存在时
            headeres.forEach(header -> {
                HdrDocument hdrDoc = HdrDocument.Factory.newInstance();
                XWPFRelation relation = XWPFRelation.HEADER;
                int i = this.getRelationIndex(relation);
                XWPFHeader wrapper = (XWPFHeader) this.doc.createRelationship(relation, XWPFFactory.getInstance(), i);
                header.setXWPFDocument(this.doc);
                CTHdrFtr hdr = this.buildHdr(type, header, pars);
                header.setHeaderFooter(hdr);
                hdrDoc.setHdr(hdr);
                this.assignHeader(header, type);
            });
        }

    }

    public XWPFFooter createFooter(STHdrFtr.Enum type) {
        return this.createFooter(type, (XWPFParagraph[])null);
    }

    public XWPFFooter createFooter(STHdrFtr.Enum type, XWPFParagraph[] pars) {
        XWPFFooter footer = this.getFooter(type);
        if (footer == null) {
            FtrDocument ftrDoc = org.openxmlformats.schemas.wordprocessingml.x2006.main.FtrDocument.Factory.newInstance();
            XWPFRelation relation = XWPFRelation.FOOTER;
            int i = this.getRelationIndex(relation);
            XWPFFooter wrapper = (XWPFFooter)this.doc.createRelationship(relation, XWPFFactory.getInstance(), i);
            wrapper.setXWPFDocument(this.doc);
            CTHdrFtr ftr = this.buildFtr(type, wrapper, pars);
            wrapper.setHeaderFooter(ftr);
            ftrDoc.setFtr(ftr);
            this.assignFooter(wrapper, type);
            footer = wrapper;
        }

        return footer;
    }

    private int getRelationIndex(XWPFRelation relation) {
        int i = 1;
        Iterator var3 = this.doc.getRelationParts().iterator();

        while(var3.hasNext()) {
            POIXMLDocumentPart.RelationPart rp = (POIXMLDocumentPart.RelationPart)var3.next();
            if (rp.getRelationship().getRelationshipType().equals(relation.getRelation())) {
                ++i;
            }
        }

        return i;
    }

    private CTHdrFtr buildFtr(STHdrFtr.Enum type, XWPFHeaderFooter wrapper, XWPFParagraph[] pars) {
        CTHdrFtr ftr = this.buildHdrFtr(pars, wrapper);
        this.setFooterReference(type, wrapper);
        return ftr;
    }

    private CTHdrFtr buildHdr(STHdrFtr.Enum type, XWPFHeaderFooter wrapper, XWPFParagraph[] pars) {
        CTHdrFtr hdr = this.buildHdrFtr(pars, wrapper);
        //this.setHeaderReference(type, wrapper);
        return hdr;
    }

    /**
     *  不为空，
     * @param type
     * @param pStyle
     * @param wrapper
     * @param pars
     * @return
     */
    private CTHdrFtr buildHdr(STHdrFtr.Enum type, String pStyle, XWPFHeaderFooter wrapper, XWPFParagraph[] pars) {
        CTHdrFtr hdr = this.buildHdrFtr( pars, wrapper);
        this.setHeaderReference(type, wrapper);
        return hdr;
    }

    /**
     *  不存在时
     * @param pStyle
     * @param paragraphs
     * @param wrapper
     * @return
     */
    private CTHdrFtr buildHdrFtr(String pStyle, XWPFParagraph[] paragraphs, XWPFHeaderFooter wrapper) {
        CTHdrFtr ftr = wrapper._getHdrFtr();
        if (paragraphs != null) {
            for(int i = 0; i < paragraphs.length; ++i) {
                CTP p = ftr.addNewP();
                ftr.setPArray(i, paragraphs[i].getCTP());
            }
        }

        return ftr;
    }

    private CTHdrFtr buildHdrFtr(XWPFParagraph[] paragraphs, XWPFHeaderFooter wrapper) {
        CTHdrFtr ftr = wrapper._getHdrFtr();
        int n = ftr.getPArray().length;

        if (paragraphs != null) {
            for(int i = 0; i < paragraphs.length; ++i) {
                ftr.addNewP();
                //ftr.setPArray(i, paragraphs[i].getCTP());
                //赋值给新加的元素
                ftr.setPArray(n+i, paragraphs[i].getCTP());
            }
        }

        return ftr;
    }

    private void setFooterReference(STHdrFtr.Enum type, XWPFHeaderFooter wrapper) {
        CTHdrFtrRef ref = this.doc.getDocument().getBody().getSectPr().addNewFooterReference();
        ref.setType(type);
        ref.setId(this.doc.getRelationId(wrapper));
    }

    private void setHeaderReference(STHdrFtr.Enum type, XWPFHeaderFooter wrapper) {
        CTHdrFtrRef ref = this.doc.getDocument().getBody().getSectPr().addNewHeaderReference();
        ref.setType(type);
        ref.setId(this.doc.getRelationId(wrapper));
    }


    public XWPFFooter getFirstPageFooter() {
        return this.firstPageFooter;
    }


    public XWPFFooter getOddPageFooter() {
        return this.defaultFooter;
    }


    public XWPFFooter getEvenPageFooter() {
        return this.evenPageFooter;
    }



    public XWPFFooter getDefaultFooter() {
        return this.defaultFooter;
    }

    public List<XWPFHeader> getHeader(int pageNumber) {
        if (pageNumber == 1 && this.firstPageHeader != null) {
            return this.firstPageHeader;
        } else {
            return pageNumber % 2 == 0 && this.evenPageHeader != null ? this.evenPageHeader : this.defaultHeader;
        }
    }

    public List<XWPFHeader> getHeader(STHdrFtr.Enum type) {
        if (type == STHdrFtr.EVEN) {
            return this.evenPageHeader;
        } else {
            return type == STHdrFtr.FIRST ? this.firstPageHeader : this.defaultHeader;
        }
    }

    public XWPFFooter getFooter(int pageNumber) {
        if (pageNumber == 1 && this.firstPageFooter != null) {
            return this.firstPageFooter;
        } else {
            return pageNumber % 2 == 0 && this.evenPageFooter != null ? this.evenPageFooter : this.defaultFooter;
        }
    }

    public XWPFFooter getFooter(STHdrFtr.Enum type) {
        if (type == STHdrFtr.EVEN) {
            return this.evenPageFooter;
        } else {
            return type == STHdrFtr.FIRST ? this.firstPageFooter : this.defaultFooter;
        }
    }

    public void createWatermark(String text) {
        XWPFParagraph[] pars = new XWPFParagraph[]{this.getWatermarkParagraph(text, 1)};
        this.createHeader(DEFAULT, pars);
        pars[0] = this.getWatermarkParagraph(text, 2);
        this.createHeader(FIRST, pars);
        pars[0] = this.getWatermarkParagraph(text, 3);
        this.createHeader(EVEN, pars);
    }

    private XWPFParagraph getWatermarkParagraph(String text, int idx) {
        CTP p = org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP.Factory.newInstance();
        byte[] rsidr = this.doc.getDocument().getBody().getPArray(0).getRsidR();
        byte[] rsidrdefault = this.doc.getDocument().getBody().getPArray(0).getRsidRDefault();
        p.setRsidP(rsidr);
        p.setRsidRDefault(rsidrdefault);
        CTPPr pPr = p.addNewPPr();
        pPr.addNewPStyle().setVal("Header");
        CTR r = p.addNewR();
        CTRPr rPr = r.addNewRPr();
        rPr.addNewNoProof();
        CTPicture pict = r.addNewPict();
        CTGroup group = com.microsoft.schemas.vml.CTGroup.Factory.newInstance();
        CTShapetype shapetype = group.addNewShapetype();
        shapetype.setId("_x0000_t136");
        shapetype.setCoordsize("1600,21600");
        shapetype.setSpt(136.0F);
        shapetype.setAdj("10800");
        shapetype.setPath2("m@7,0l@8,0m@5,21600l@6,21600e");
        CTFormulas formulas = shapetype.addNewFormulas();
        formulas.addNewF().setEqn("sum #0 0 10800");
        formulas.addNewF().setEqn("prod #0 2 1");
        formulas.addNewF().setEqn("sum 21600 0 @1");
        formulas.addNewF().setEqn("sum 0 0 @2");
        formulas.addNewF().setEqn("sum 21600 0 @3");
        formulas.addNewF().setEqn("if @0 @3 0");
        formulas.addNewF().setEqn("if @0 21600 @1");
        formulas.addNewF().setEqn("if @0 0 @2");
        formulas.addNewF().setEqn("if @0 @4 21600");
        formulas.addNewF().setEqn("mid @5 @6");
        formulas.addNewF().setEqn("mid @8 @5");
        formulas.addNewF().setEqn("mid @7 @8");
        formulas.addNewF().setEqn("mid @6 @7");
        formulas.addNewF().setEqn("sum @6 0 @5");
        CTPath path = shapetype.addNewPath();
        path.setTextpathok(STTrueFalse.T);
        path.setConnecttype(STConnectType.CUSTOM);
        path.setConnectlocs("@9,0;@10,10800;@11,21600;@12,10800");
        path.setConnectangles("270,180,90,0");
        CTTextPath shapeTypeTextPath = shapetype.addNewTextpath();
        shapeTypeTextPath.setOn(STTrueFalse.T);
        shapeTypeTextPath.setFitshape(STTrueFalse.T);
        CTHandles handles = shapetype.addNewHandles();
        CTH h = handles.addNewH();
        h.setPosition("#0,bottomRight");
        h.setXrange("6629,14971");
        CTLock lock = shapetype.addNewLock();
        lock.setExt(STExt.EDIT);
        CTShape shape = group.addNewShape();
        shape.setId("PowerPlusWaterMarkObject" + idx);
        shape.setSpid("_x0000_s102" + (4 + idx));
        shape.setType("#_x0000_t136");
        //shape.setStyle("position:absolute;margin-left:0;margin-top:0;width:415pt;height:207.5pt;z-index:-251654144;mso-wrap-edited:f;mso-position-horizontal:center;mso-position-horizontal-relative:margin;mso-position-vertical:center;mso-position-vertical-relative:margin");
        //shape.setStyle("position:absolute;margin-left:0;margin-top:0;width:415pt;height:207.5pt;z-index:-251654144;mso-wrap-edited:f;mso-position-horizontal:center;mso-position-horizontal-relative:margin;mso-position-vertical:center;mso-position-vertical-relative:margin;rotation:315;height:30pt");
//        shape.setStyle("width:200pt;rotation:0;height:12pt");
        shape.setStyle("position:absolute;margin-left:0;margin-top:0;width:" + width + "pt;height:207.5pt;z-index:-251654144;mso-wrap-edited:f;mso-position-horizontal:center;mso-position-horizontal-relative:margin;mso-position-vertical:center;mso-position-vertical-relative:margin;rotation:315;size:" + size + ";height:" + height );
        shape.setWrapcoords("616 5068 390 16297 39 16921 -39 17155 7265 17545 7186 17467 -39 17467 18904 17467 10507 17467 8710 17545 18904 17077 18787 16843 18358 16297 18279 12554 19178 12476 20701 11774 20779 11228 21131 10059 21248 8811 21248 7563 20975 6316 20935 5380 19490 5146 14022 5068 2616 5068");
        //shape.setFillcolor("black");
        shape.setFillcolor("#555555");
        shape.setStroked(STTrueFalse.FALSE);
        CTTextPath shapeTextPath = shape.addNewTextpath();
        shapeTextPath.setStyle("font-family:&quot;Cambria&quot;;font-size:1pt");
        shapeTextPath.setString(text);
        pict.set(group);
        return new XWPFParagraph(p, this.doc);
    }
    public void setHeight(int height) {
        this.height = height;
    }

    static {
        DEFAULT = STHdrFtr.DEFAULT;
        EVEN = STHdrFtr.EVEN;
        FIRST = STHdrFtr.FIRST;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}