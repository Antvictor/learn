package antvictor.study.ase;

import com.alibaba.fastjson.JSON;

import javax.crypto.*;
import java.io.*;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class ASE {
    static final String algorithm="AES";

    public static SecretKey generateKey() throws NoSuchAlgorithmException {
        KeyGenerator secretGenerator=KeyGenerator.getInstance(algorithm);
        SecureRandom secureRandom=new SecureRandom();
        secretGenerator.init(secureRandom);
        SecretKey secretKey=secretGenerator.generateKey();
        return secretKey;

    }
    final static String charsetName="Utf-8";
    static Charset charset=Charset.forName(charsetName);

    public static byte[] encrypt(String content,SecretKey secretKey) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        return aes(content.getBytes(charset),Cipher.ENCRYPT_MODE, secretKey);
    }

    public static byte[] encrypt(byte[] content,SecretKey secretKey) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        return aes(content,Cipher.ENCRYPT_MODE, secretKey);
    }


    public static String decrypt(byte[] contentArray,SecretKey secretKey) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, UnsupportedEncodingException {
        byte[] result= aes(contentArray,Cipher.DECRYPT_MODE,secretKey);
        System.out.println("总有对的吧" + result);
        return new String(result,charsetName);

    }

    private static byte[] aes(byte[] contentArray,int mode,SecretKey secretKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, BadPaddingException {
        Cipher cipher=Cipher.getInstance(algorithm);
        cipher.init(mode, secretKey);
        byte[] result=cipher.doFinal(contentArray);
        return result;
    }

    public static void main(String[] args) {
        try {
            File file = new File("/Users/exccedy/Downloads/video_test.mp4");
            FileInputStream fileInputStream = new FileInputStream(file);

            RandomAccessFile randomAccessFile = new RandomAccessFile(file,"rw");
            byte[] buffer = new byte[(int) randomAccessFile.length()];
//           String buffer ="你好，hello";
            fileInputStream.read(buffer);
            SecretKey secretKey=generateKey();

            System.out.println(JSON.toJSONString(buffer));
            System.out.println(new String(buffer, charsetName));
            byte[] encryptResult= encrypt(buffer,secretKey);
//            System.out.println("加密后的结果为:"+ encryptResult);
            System.out.println("加密后的长度为:"+ encryptResult.length);
            System.out.println("加密后的结果为:"+ Base64.getEncoder().encodeToString(encryptResult));
//            String decrypetResult=decrypt(encryptResult,secretKey);
//            System.out.println("解密后的结果为:"+decrypetResult);
//            System.out.println("解密后的结果为:"+JSON.toJSONString(decrypetResult.getBytes()));
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
    private static byte[] encrypt(String content) {
        // TODO Auto-generated method stub
        return null;
    }
}
