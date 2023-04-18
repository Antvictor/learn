package antvictor.study.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;



public class ImageTest {
    public static void main(String[] args) {
        // 原图片地址
        String path = "/Users/exccedy/Downloads/up.jpeg";
        // 当前项目路径
        String absolutePath = new File("").getAbsolutePath();
        // 目标图片存放地址
        String targerPath = absolutePath + "/src/main/webapp/pic/";
        File file = new File(targerPath);
        if (!file.exists()) {// 目录不存在就创建目录
            file.mkdirs();
        }
        String targerImage = targerPath + "targer.jpeg";
        try {
            // 将图片转化成字节码
            byte[] by = imageToByte(path);
            // 将图片字节码进行压缩
            byte[] decompressPicByte = decompressPicByte(by);
            // 将字节码转化成目标图片
//            bufferToImage(decompressPicByte, targerImage);
            // 将图片字节进行base64编码（在word文档中显示图片时会用到base64转码）
//            System.out.println(converPicBase64(decompressPicByte).length());
            System.out.println(org.apache.axis.encoding.Base64.encode(decompressPicByte));
            System.out.println(decompressPicByte.length);
            System.out.println(org.apache.axis.encoding.Base64.encode(decompressPicByte).length());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("OK");

    }

    /**
     *
     * 功能描述: <br>
     * 〈功能详细描述〉将图片字节码转换为图片
     *
     * @param by 图片的字节码
     * @param targerImage 目标图片的地址，带图片的格式
     * @throws Exception
     */
    private static void bufferToImage(byte[] by, String targerImage) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(targerImage));
        fileOutputStream.write(by);
        fileOutputStream.close();

    }

    /**
     *
     * 功能描述: <br>
     * 〈功能详细描述〉将图片转换为字节码
     *
     * @param path 图片的路径地址
     * @return 图片的字节码
     * @throws Exception
     */
    private static byte[]  imageToByte(String path) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(new File(path));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = fileInputStream.read(bytes)) != -1) {
            byteArrayOutputStream.write(bytes, 0, len);
        }
        fileInputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    /**
     *
     * 功能描述: <br>
     * 〈功能详细描述〉图片字节码压缩
     *
     * @param picByte 图片的字节码
     * @return 压缩后的图片字节码
     */
    private static byte[] decompressPicByte(byte[] picByte) {
        ByteArrayInputStream intputStream = new ByteArrayInputStream(picByte);
        double scale = 64512.0 / picByte.length;
        System.out.println(scale);
        Builder<? extends InputStream> builder = Thumbnails.of(intputStream).scale(scale);
        try {
            BufferedImage bufferedImage = builder.asBufferedImage();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpeg", baos);
            byte[] byteArray = baos.toByteArray();
            return byteArray;
        } catch (IOException e) {
        }
        return picByte;

    }

    /**
     *
     * 功能描述: <br>
     * 〈功能详细描述〉将图片字节码进行base64 编码
     *
     * @param data 图片字节码
     * @return base64 编码后的字符串
     */
    private static String converPicBase64(byte[] data) {

        Base64.Encoder encoder = Base64.getEncoder();
        return String.valueOf(encoder.encode(data));
    }

}
