package larry.study.pdf;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import java.io.FileNotFoundException;

/**
 * @author exccedy
 * @date 2021/10/26
 **/
public class IText7Demo {
    public static void main(String[] args) {
        try {
            PdfDocument pdfDocument = new PdfDocument(new PdfWriter(""));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
