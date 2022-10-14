package larry.study.ase;

import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.*;
import java.util.Base64;

@Log4j2
public class RSAUtils {

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    public static void generate() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

            keyPairGenerator.initialize(1024);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            System.out.println("未转化时私钥：" + JSON.toJSONString(keyPair.getPrivate()));
            System.out.println("未转化时公钥：" + JSON.toJSONString(keyPair.getPublic()));
            String privateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
            String publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
            System.out.println("私钥:" + privateKey);
            System.out.println("私钥:" + privateKey.length());
            System.out.println("公钥:" + publicKey);
            System.out.println("公钥:" + publicKey.length());

            // 通过私钥获得公钥呢

            // 字符串专私钥：1. 转化为数组 2. 新建PKCS8对象 3. 创建key工厂 4. 转化为私钥
            // 私钥专公钥： 5. 通过私钥读取规范 6. 新建公钥规范 7. 工厂生成公钥。
            byte[] keyPr = Base64.getDecoder().decode(privateKey);

            PKCS8EncodedKeySpec keyGenParameterSpec = new PKCS8EncodedKeySpec(keyPr);

            KeyFactory kf = KeyFactory.getInstance("RSA");

            PrivateKey privateKey1 = kf.generatePrivate(keyGenParameterSpec);
            System.out.println("字符串转化为私钥：" + JSON.toJSONString(privateKey1));


            RSAPrivateCrtKeySpec spec = kf.getKeySpec(privateKey1, RSAPrivateCrtKeySpec.class);
            RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(spec.getModulus(), BigInteger.valueOf(65537));

            kf.generatePublic(publicKeySpec);

            PublicKey publicKey1 = kf.generatePublic(publicKeySpec);
            System.out.println("私钥获取公钥：" + JSON.toJSONString(publicKey1));

            System.out.println(keyPair.getPrivate().equals(privateKey1));
            System.out.println(keyPair.getPublic().equals(publicKey1));
        } catch (NoSuchAlgorithmException e) {
            log.error("init key pair error:{}", e.getMessage());
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            log.error("转化为字节数组");
            e.printStackTrace();
        }

    }

    public static void gen() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

            keyPairGenerator.initialize(1024);
            KeyPair keyPair = keyPairGenerator.genKeyPair();
            System.out.println("未转化时私钥：" + JSON.toJSONString(keyPair.getPrivate()));
            System.out.println("未转化时公钥：" + JSON.toJSONString(keyPair.getPublic()));
            String privateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
            String publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
            System.out.println("私钥:" + privateKey);
            System.out.println("私钥:" + privateKey.length());
            System.out.println("公钥:" + publicKey);
            System.out.println("公钥:" + publicKey.length());

            // 通过私钥获得公钥呢

            // 字符串专私钥：1. 转化为数组 2. 新建PKCS8对象 3. 创建key工厂 4. 转化为私钥
            // 私钥专公钥： 5. 通过私钥读取规范 6. 新建公钥规范 7. 工厂生成公钥。
//            byte[] keyPr = Base64.getDecoder().decode(privateKey);
//
//            PKCS8EncodedKeySpec keyGenParameterSpec = new PKCS8EncodedKeySpec(keyPr);
//
//            KeyFactory kf = KeyFactory.getInstance("RSA");
//
//            PrivateKey privateKey1 = kf.generatePrivate(keyGenParameterSpec);
            // 精简为一行
            PrivateKey privateKey1 = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey)));
            System.out.println("字符串转化为私钥：" + JSON.toJSONString(privateKey1));


//            RSAPrivateCrtKeySpec spec = kf.getKeySpec(privateKey1,RSAPrivateCrtKeySpec.class);
//            RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(spec.getModulus(), BigInteger.valueOf(65537));
//
//            kf.generatePublic(publicKeySpec);
//
//            PublicKey publicKey1 = kf.generatePublic(publicKeySpec);

            RSAPrivateKeySpec spec = KeyFactory.getInstance("RSA").getKeySpec(privateKey1, RSAPrivateKeySpec.class);
            PublicKey publicKey1 = KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(spec.getModulus(), BigInteger.valueOf(65537)));
            System.out.println("私钥获取公钥：" + JSON.toJSONString(publicKey1));

            System.out.println(keyPair.getPrivate().equals(privateKey1));
            System.out.println(keyPair.getPublic().equals(publicKey1));

        } catch (NoSuchAlgorithmException e) {
            log.error("init key pair error:{}", e.getMessage());
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            log.error("转化为字节数组");
            e.printStackTrace();
        }

    }

    public static byte[] encrypt(String str, String publicKey) {
        try {
            PublicKey privateKey1 = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey)));
            return encrypt(str.getBytes(), privateKey1);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] encrypt(byte[] data, PublicKey publicKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offset > 0) {
            if (inputLen - offset > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offset, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        // 获取加密内容使用base64进行编码,并以UTF-8为标准转化成字符串
        // 加密后的字符串
        return encryptedData;
    }

    public static byte[] decrypt(byte[] data, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] dataBytes = data;
        int inputLen = dataBytes.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;

        // 对数据分段解密
        while (inputLen - offset > 0) {
            if (inputLen - offset > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(dataBytes, offset, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(dataBytes, offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        // 解密后的内容
        return decryptedData;
    }


    /**
     * 129      * 签名
     * 130      *
     * 131      * @param data 待签名数据
     * 132      * @param privateKey 私钥
     * 133      * @return 签名
     * 134
     */
    public static String sign(String data, PrivateKey privateKey) throws Exception {
        byte[] keyBytes = privateKey.getEncoded();
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey key = keyFactory.generatePrivate(keySpec);
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(key);
        signature.update(data.getBytes());
        return Base64.getEncoder().encodeToString(signature.sign());
    }

    /**
     * 147      * 验签
     * 148      *
     * 149      * @param srcData 原始字符串
     * 150      * @param publicKey 公钥
     * 151      * @param sign 签名
     * 152      * @return 是否验签通过
     * 153
     */
    public static boolean verify(String srcData, PublicKey publicKey, String sign) throws Exception {
        byte[] keyBytes = publicKey.getEncoded();
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey key = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initVerify(key);
        signature.update(srcData.getBytes());
        return signature.verify(Base64.getDecoder().decode(sign.getBytes()));
    }

    /**
     * 26      * 获取密钥对
     * 27      *
     * 28      * @return 密钥对
     * 29
     */
    public static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(1024);
        return generator.generateKeyPair();
    }

    public static PublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey)));
    }
    public static PrivateKey getPrivateKey(String privateKey) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        return KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey)));
    }

    public static void main(String[] args) {
        try {
            // 生成密钥对
            KeyPair keyPair = getKeyPair();
            String privateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
            String publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
            System.out.println("私钥:" + privateKey);
            System.out.println("公钥:" + publicKey);
            // RSA加密
            String data = "待加密的文字内容";
            System.out.println("加密前内容：" + JSON.toJSONString(data.getBytes()));
            byte[] encryptData = encrypt(data.getBytes(), getPublicKey(publicKey));
            System.out.println("加密后内容:" + encryptData);
            System.out.println("加密后内容:" + JSON.toJSONString(encryptData));
            // RSA解密
            byte[] decryptData = decrypt(encryptData, getPrivateKey(privateKey));
            System.out.println("解密后内容:" + decryptData);
            System.out.println("解密后内容:" + JSON.toJSONString(decryptData));

            // RSA签名
            String sign = sign(data, getPrivateKey(privateKey));
            // RSA验签
            boolean result = verify(data, getPublicKey(publicKey), sign);
            System.out.print("验签结果:" + result);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("加解密异常");
        }
    }
}
