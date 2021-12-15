//package yangchao.study.pdf;
//
//import com.alibaba.fastjson.JSONArray;
//import com.itextpdf.text.Chunk;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.ColumnText;
//import com.itextpdf.text.pdf.PdfReader;
//import com.itextpdf.text.pdf.PdfStamper;
//import com.itextpdf.text.pdf.PdfWriter;
//
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
///**
// * @author exccedy
// * @date 2021/10/26
// **/
//public class PdfText {
//
//    public static void main(String[] args) throws IOException, DocumentException {
//        FileOutputStream out = new FileOutputStream("/Users/exccedy/project/common/study/img/createSamplePDF.pdf");
////        FileOutputStream out = new FileOutputStream("/Users/exccedy/Downloads/加密.pdf");
//        FileOutputStream out2 = new FileOutputStream("/Users/exccedy/project/common/study/img/insertPDF.pdf");
//        //Step 1—Create a Document.
//        Document document = new Document();
////Step 2—Get a PdfWriter instance.
//
//      /*  PdfWriter.getInstance(document, out);
////Step 3—Open the Document.
//        document.open();
////Step 4—Add content.
//        document.add(new Paragraph("Hello World"));*/
////Step 5—Close the Document.
////        document.close();
//
//       /* document.open();
//        document.add(new Paragraph("Hello World"));
//        document.close();*/
//        //标题
//        document.addTitle("this is a title");
////作者
//        document.addAuthor("tt");
////主题
//        document.addSubject("subject");
////关键字
//        document.addKeywords("Keywords");
////创建时间
//        document.addCreationDate();
////应用程序
//        document.addCreator("tt");
//        PdfWriter writer = PdfWriter.getInstance(document, out);
//// 设置密码为："World"
//        writer.setEncryption("123456".getBytes(), "123456".getBytes(),
//                PdfWriter.ALLOW_SCREENREADERS,
//                PdfWriter.STANDARD_ENCRYPTION_128);
//
//        PdfReader pdfReader = new PdfReader("/Users/exccedy/Downloads/加密.pdf", "123456".getBytes());
//        System.out.println(JSONArray.toJSONString(pdfReader.getInfo()));
//        PdfStamper pdfStamper = new PdfStamper(pdfReader, out2);
//        pdfStamper.insertPage(2, pdfReader.getPageSize(1));
//
//        ColumnText columnText = new ColumnText(null);
//        columnText.addElement(new Paragraph(24, new Chunk("insert")));
//        columnText.setCanvas(pdfStamper.getOverContent(2));
//        columnText.setSimpleColumn(36,110,200,300);
//
//        pdfStamper.close();
//        pdfReader.close();
//
//    }
//
//}
