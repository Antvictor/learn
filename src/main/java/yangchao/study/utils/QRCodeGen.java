package yangchao.study.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.encoder.QRCode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class QRCodeGen {

    public static void genQRCodeImg(String text) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 225, 225);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", new File("/Users/exccedy/project/common/study/img/img1.png").toPath());
    }

    public static void main(String[] args) {
        try {
//            genQRCodeImg("https://open.tdchain.cn/api/cloud/jbcc/trans?hash=806b090e244694c16da79b93aeca06426a6315d0dae7d074d913fc940aadb811330033&null&_=1598857888679");
            genQRCodeImg("https://inhener.chinasandbox.com/tocDiary/#/diary/detail/266");
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}