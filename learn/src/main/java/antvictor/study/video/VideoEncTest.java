package antvictor.study.video;

import java.io.*;

public class VideoEncTest {

    private static String privateKey = "0x99";

    private final static String path = "/Users/exccedy/Downloads/";
    private static final int numOfEncAndDec = 0x9945; //加密解密秘钥
     private static int dataOfFile = 0; //文件字节内容
    public static void main(String[] args) {
//        encFile(path + "video_test.mp4", path + "video_enc.mp4");
//        decFile(path + "video_enc.mp4", path + "video_dec.mp4");
//        System.out.println(100 >> 10);
//        System.out.println(100 >> 2);
//        encFile2(new File(path + "video_test.mp4"), new File(path + "video_enc_2.mp4"));
        encFile3(path + "video_test.mp4", path + "video_enc_6.mp4");
        encFile3(path + "video_enc_6.mp4", path + "video_dec_6.mp4");
//        decFile3(path + "video_enc_3.mp4", path + "video_dec_3.mp4");
    }


    public static void encFile2(File srcFile, File encFile) {
        try {

            if(!srcFile.exists()){
                System.out.println("source file not exixt");
                return;
            }

            if(!encFile.exists()){
                System.out.println("encrypt file created");
                encFile.createNewFile();
            }
            InputStream fis  = new FileInputStream(srcFile);
            OutputStream fos = new FileOutputStream(encFile);
            while ((dataOfFile = fis.read()) > -1) {
                fos.write(dataOfFile^numOfEncAndDec);
            }

            fis.close();
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void encFile3(String srcFile, String encFile) {
        File file = new File(srcFile);
        if (!file.exists()) {
            System.out.println("file not exit");
            return;
        }

        File enc = new File(encFile);
        // 输入流

        try {
            FileInputStream in = new FileInputStream(file);
            FileOutputStream out = new FileOutputStream(enc);

            byte[] buffer = new byte[1024], buffer2 = new byte[1024];
            int r;

            while ((r = in.read(buffer)) > 0) {
                for (int i = 0; i < r; i++) {
                    byte b = buffer[i];
                    buffer2[i] = (byte) (b^ numOfEncAndDec);
                }
                out.write(buffer2,0,r);
                out.flush();
            }

            in.close();
            out.close();


            append(encFile);

//            enc.setReadable(false);
//            enc.setWritable(false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("加密成功");

    }

    public static void encFile(String srcFile, String encFile) {
        File file = new File(srcFile);
        if (!file.exists()) {
            System.out.println("file not exit");
            return;
        }

        File enc = new File(encFile);
        // 输入流
        try {
            FileInputStream in = new FileInputStream(file);
            FileOutputStream out = new FileOutputStream(enc);

            byte[] buffer = new byte[1024], buffer2 = new byte[1024];
            int r;

            while ((r = in.read(buffer)) > 0) {
                for (int i = 0; i < r; i++) {
                    byte b = buffer[i];
                    buffer2[i] = b == 255 ? 0 : ++b;
                }
                out.write(buffer2,0,r);
                out.flush();
            }

            in.close();
            out.close();


            append(encFile);

            enc.setReadable(false);
            enc.setWritable(false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("加密成功");

    }


    public static void decFile3(String encFile, String decFile) {
        // 读取加密文件
        File file = new File(encFile);
        if (!file.exists()) {
            System.out.println("enc file not exist");
            return;
        }
        // 读取输出文件，不存在就创建
        File dec = new File(decFile);
        if (!dec.getParentFile().exists()) {
            dec.getParentFile().mkdirs();
        }
        if (!file.canRead()) {
            file.setReadable(true);
        }
        try {
            FileInputStream in = new FileInputStream(file);
            FileOutputStream out = new FileOutputStream(dec);

            // 两个1024数组
            byte[] buffer = new byte[1024], buffer2 = new byte[1024];
            // 最大值是255，解密过程就是将255改为0
            byte bMax = (byte) 255;
            long size = file.length() - privateKey.length();
            int mod = (int) (size % 1024);
            int div = (int) (size >> 10);
            int count = mod == 0 ? div : (div + 1);
            int k = 1, r;
            while ((k <= count && (r = in.read(buffer)) > 0)) {
                if (mod != 0 && k == count) {
                    r = mod;
                }

                for (int i = 0; i < r; i++) {
                    byte b = buffer[i];
                    buffer2[i] = (byte) (b^numOfEncAndDec);
                }
                out.write(buffer2, 0, r);
                k++;
            }
            out.close();
            in.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("解密成功");
    }


    public static void append(String encFile) {
        try {
            // 打开一个随机文件流，按读写方式
            RandomAccessFile randomAccessFile = new RandomAccessFile(encFile,"rw");
            // 获取文件长度
            long length = randomAccessFile.length();
            // 将写指针移至文件末
            randomAccessFile.seek(length);
            randomAccessFile.writeBytes(privateKey);
            randomAccessFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("读取报错");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("获取长度报错");
        }
    }

    public static void decFile(String encFile, String decFile) {
        // 读取加密文件
        File file = new File(encFile);
        if (!file.exists()) {
            System.out.println("enc file not exist");
            return;
        }
        // 读取输出文件，不存在就创建
        File dec = new File(decFile);
        if (!dec.getParentFile().exists()) {
            dec.getParentFile().mkdirs();
        }
        if (!file.canRead()) {
            file.setReadable(true);
        }
        try {
            FileInputStream in = new FileInputStream(file);
            FileOutputStream out = new FileOutputStream(dec);

            // 两个1024数组
            byte[] buffer = new byte[1024], buffer2 = new byte[1024];
            // 最大值是255，解密过程就是将255改为0
            byte bMax = (byte) 255;
            long size = file.length() - privateKey.length();
            int mod = (int) (size % 1024);
            int div = (int) (size >> 10);
            int count = mod == 0 ? div : (div + 1);
            int k = 1, r;
            while ((k <= count && (r = in.read(buffer)) > 0)) {
                if (mod != 0 && k == count) {
                    r = mod;
                }

                for (int i = 0; i < r; i++) {
                    byte b = buffer[i];
                    buffer2[i] = b == 0 ? bMax : --b;
                }
                out.write(buffer2, 0, r);
                k++;
            }
            out.close();
            in.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("解密成功");
    }



    public  static void aesEncFile() {

    }

}
