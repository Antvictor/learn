package yangchao.study.utils;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.*;

public class WaterMark {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        try {
//            setWordDOCXWaterMark( new FileInputStream(new File("/Users/exccedy/Downloads/管理.docx")),"3323.docx", "0000000000000ssdsda");
            DocxOperation.docxAddWatermark("/Users/exccedy/Downloads/管理1.docx","/Users/exccedy/Downloads/446a61.docx","天德链\n\n0sjjkhsdjkfhakhdskafhs");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void setWordDOCXWaterMark(InputStream inputStream, String outPath, String markStr) throws Exception {


        XWPFDocument doc = null;
        try {
            doc = new XWPFDocument(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        XWPFParagraph paragraph = doc.createParagraph();
        System.out.println(paragraph.getPictureText() + "---");

        XWPFRun run=paragraph.createRun();


        run.setText(" 不能？");


        // create header-footer


        XWPFHeaderFooterPolicy headerFooterPolicy = doc.getHeaderFooterPolicy();
        if (headerFooterPolicy == null) {
          headerFooterPolicy = doc.createHeaderFooterPolicy();
        }
        // create default Watermark - fill color black and not rotated
        headerFooterPolicy.createWatermark(markStr);
        // get the default header
        // Note: createWatermark also sets FIRST and EVEN headers
        // but this code does not updating those other headers


            XWPFHeader header = headerFooterPolicy.getHeader(XWPFHeaderFooterPolicy.DEFAULT);
//            System.out.println(header.getParagraphs().size());

        paragraph = header.getParagraphArray(0);
            System.out.println(paragraph.getCTP().getRArray(0));
            System.out.println(paragraph.getCTP().getRArray(0).sizeOfPictArray());
        if (paragraph.getCTP().getRArray(0).sizeOfPictArray() < 1) {
            // 去掉旧页眉
            System.out.println(paragraph.getCTP().sizeOfRArray());
            paragraph.getCTP().removeR(0);
            System.out.println(paragraph.getCTP().sizeOfRArray());
            paragraph.getCTP().insertNewR(0);
            paragraph.getCTP().addNewProofErr();

            paragraph.getCTP().getRArray(0).addNewRPr();
            paragraph.getCTP().getRArray(0).addNewPict();
//            paragraph.getCTP().getRArray(0).insertNewPict(0);

            // 然后生成文件再读取
            File file = new File( "/Users/exccedy/Downloads/" + outPath);
            if(!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                doc.write(new FileOutputStream(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            setWordDOCXWaterMark(new FileInputStream(new File("/Users/exccedy/Downloads/" + outPath)), outPath,markStr);

        }
            System.out.println(paragraph.getCTP().getRArray(0).getPictArray(0));

            // get com.microsoft.schemas.vml.CTShape where fill color and rotation is set

            org.apache.xmlbeans.XmlObject[] xmlobjects = paragraph.getCTP().getRArray(0).getPictArray(0).selectChildren(
                    new javax.xml.namespace.QName("urn:schemas-microsoft-com:vml", "shape"));
            if (xmlobjects.length > 0) {
                com.microsoft.schemas.vml.CTShape ctshape = (com.microsoft.schemas.vml.CTShape)xmlobjects[0];
                // set fill color
                //ctshape.setFillcolor("#d8d8d8");
                ctshape.setFillcolor("#000000");
                // set rotation
                ctshape.setStyle(ctshape.getStyle() + ";rotation:315;size:50;height:12");
                //System.out.println(ctshape);
            }

        System.out.println("___"  + paragraph.getCTP().getRArray(0).getPictArray(0));


        File file = new File("/Users/exccedy/Downloads/" + outPath);
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            doc.write(new FileOutputStream(file));
            doc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
