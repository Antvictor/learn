package larry.study.video;

import java.io.*;
import java.util.Base64;

public class VideosTest {
    private  static  final String prk = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAM0sM7tMat+W+0cCMzZMmpMmcMkS\n" +
            "Nx5mjJKHUkC8qfxxKuJTh+wMJNwjINMxxH+wkA7BCTr5eA86P7jHjLERPeaHAqkHZRpeihKN3qoc\n" +
            "WTxVEgdTjnaHs0yHcznN7rb2tPqxMyIAIQwF21bnGJsxn3BtcjpOBfyeVbJQWt7jo/x3AgMBAAEC\n" +
            "gYBB0llyUCzFOGm6uSD23ZKFfMKYLyjRrvKQG6uuqxgZrbClVUj42FiG6xofxwqUMG+23AT7D4eN\n" +
            "0xEYVaR0NeTWWgc17ql1E+w+evSpGFxJDMq6ULXs0+NPOHW9GpeOjCozJeKIti6G4jKzefrRZZzT\n" +
            "i2u/GqYjalGjcjnIoNeJ2QJBAOr/r+AvY/HcPBRsnEM36AYoPOEqi/XZoPZctgjzqCBtZClPOJ1z\n" +
            "ku5Ouv4DZ40YRpWU53gXQ6n/XVTujFQNGg0CQQDfgibgIkVOFs1QzOYCEslFEMoHCz6GgCarMZlz\n" +
            "av1z/4n21FsNPGG3qyBBTm+51U6WWq5bkrhQ00jknV5/d2OTAkA1uLn2IB9S/Svm52VZoIA0WCKC\n" +
            "vv07+MJpT4x1w23d6+QH5YplxDQEso7g3tpfUPth2qaHt5nKVQx7DAWceVpJAkAFWddWbFueZ/tY\n" +
            "YN9s00hQR/ueOY2wqKbEWHalBb21pjuGVkUrxvNL82PdTGRuXYX5IYilFpklDjnjh0T45kHTAkEA\n" +
            "vlWu83iKF8QMvDdY5JGkp52FNPWJCJeN+9uH3p4GFP7X2XPmsl+T2eQQCrfeAdxJn7YgNmdadLYR\n" +
            "ZzMZJkzBog==";

    public static void upload(String path, String outPath) {
        Long start = System.currentTimeMillis();
        File file = new File(path);
        File out = new File(outPath);
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(file);
            outputStream = new FileOutputStream(out);

            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            byte[] b = new byte[(int) randomAccessFile.length()];

            if (inputStream.read(b) >0) {
                byte[] data = RSAUtil.encrypt(b, RSAUtil.toPublicKey(RSAUtil.generatePublicKey(prk)));
                outputStream.write(data);
                String test = Base64.getEncoder().encodeToString(data);
//                System.out.println("数据" + test);
                System.out.println("大小" + test.length());
            }

            // 只加密前5个字节，并将加密后的内容拼接到最后
//            byte[] pri = new byte[5];
//            long len = 0;
//            if (inputStream.read(pri) > 0) {
//                byte[] data = RSAUtil.encrypt(pri, RSAUtil.toPublicKey(RSAUtil.generatePublicKey(prk)));
//                len = data.length;
//                outputStream.write(data);
//            }
//
//            byte[] b = new byte[1024];
//            while (inputStream.read(b) > 0) {
//                outputStream.write(b);
//            }

//            System.out.println(len);
            randomAccessFile.close();
            outputStream.close();
            inputStream.close();
            long time = System.currentTimeMillis() - start;
            System.out.println("加密耗时：" + time);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            System.out.println("读取长度失败");
            e.printStackTrace();
        }
    }

    public static void dec(String path, String outPath) {

        // len  加密后内容的长度
        int len = Integer.parseInt(path.substring(path.lastIndexOf("_") + 1));

        Long start = System.currentTimeMillis();
        File file = new File(path);
        File out = new File(outPath);
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(file);
            outputStream = new FileOutputStream(out);

            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
//            byte[] b = new byte[(int) randomAccessFile.length()];
            System.out.println(file.length());
            System.out.println(randomAccessFile.length());

            byte[] pri = new byte[len];

            if (inputStream.read(pri) > 0) {
                byte[] data = RSAUtil.decrypt(pri, RSAUtil.toPrivateKey(prk));
                outputStream.write(data);
            }


            byte[] b  = new byte[1024];

//            if (inputStream.read(b) >0) {
//                byte[] data = RSAUtil.decrypt(b, RSAUtil.toPrivateKey(prk));
//                outputStream.write(data);
//            }

            while (inputStream.read(b) > 0) {
                outputStream.write(b);
            }

            randomAccessFile.close();
            outputStream.close();
            inputStream.close();


            long time = System.currentTimeMillis() - start;
            System.out.println("解密耗时：" + time);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            System.out.println("读取长度失败");
            e.printStackTrace();
        }
    }

    public static void main(String [] args) {
        upload("/Users/exccedy/Downloads/video_test.mp4", "/Users/exccedy/Downloads/video_test10.mp4_128");
//        dec("/Users/exccedy/Downloads/video_test10.mp4_128", "/Users/exccedy/Downloads/video_test11.mp4");
    }
}
