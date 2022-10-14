package larry.study.video;

import lombok.extern.log4j.Log4j2;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.*;
import java.util.Base64;

@Log4j2
public class RSAUtil {

    private static final String TYPE = "RSA";
    private static final int SIZE = 1024;
    private static final int INDEX = 65537;

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;
    // 生成私钥
    public static String generatePrivateKeyStr() {
        String privateKey = null;
        // 创建密钥生成器
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance(TYPE);
            // 初始化
            keyPairGenerator.initialize(SIZE);

            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            privateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());

        } catch (NoSuchAlgorithmException e) {
            log.error("create keyPairGenerator fial:{}", e.getMessage());
        }
        return privateKey;
    }

    // 根据私钥生成公钥
    public static String generatePublicKey(String privateKey) {
        return generatePublicKey(toPrivateKey(privateKey));
    }

    // 私钥生成公钥
    public static String generatePublicKey(PrivateKey privateKey) {
        String publicKey = null;
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(TYPE);
            RSAPrivateKeySpec privateKeySpec = keyFactory.getKeySpec(privateKey, RSAPrivateKeySpec.class);
            RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(privateKeySpec.getModulus(), BigInteger.valueOf(INDEX));
            PublicKey key = keyFactory.generatePublic(rsaPublicKeySpec);
            publicKey = Base64.getEncoder().encodeToString(key.getEncoded());

        } catch (InvalidKeySpecException e) {
            log.error("create KeyFactory error:{}" + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            log.error("generate PublicKey error:{}" + e.getMessage());
        }

        return publicKey;
    }

    // 私钥字符串转化为私钥
    public static PrivateKey toPrivateKey(String privateKey) {
        PrivateKey key = null;
        try {
            // 创建key工厂
            KeyFactory kf = KeyFactory.getInstance(TYPE);
            // 转化为字节
            byte[] b = Base64.getDecoder().decode(privateKey);
            // 创建PKCS8EncodedKeySpec对象
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(b);

            key = kf.generatePrivate(spec);

        } catch (NoSuchAlgorithmException e) {
            log.error("create KeyFactory error:{}" + e.getMessage());
        }  catch (InvalidKeySpecException e) {
            log.error("generate PrivateKey error:{}" + e.getMessage());
        }
        return key;
    }

    public static PublicKey toPublicKey(String publicKey)  {
        PublicKey pubKey = null;
        try {
            pubKey =  KeyFactory.getInstance(TYPE).generatePublic(new X509EncodedKeySpec( Base64.getDecoder().decode(publicKey)));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            log.error("keyFactory instance error:{}" + e.getMessage());
        } catch (InvalidKeySpecException e) {
            log.error("generate PublicKey error:{}" + e.getMessage());
            e.printStackTrace();
        }
        return pubKey;
    }

    // 根据公钥加密

    public static byte[] encrypt(String data, String publicKey) throws Exception {
        // 将数据转为字节数组
        byte[] b = data.getBytes();
        return encrypt(b, toPublicKey(publicKey));
    }

    public static byte[] encrypt(byte[] data, PublicKey publicKey) {
        // 加密，首先初始化规则

        try {
            Cipher cipher = Cipher.getInstance(TYPE);
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
            return encryptedData;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("encrypt error:{}" + e.getMessage());
        }
        // 获取加密内容使用base64进行编码,并以UTF-8为标准转化成字符串
        // 加密后的字符串
        return null;
    }

    // 根据私钥解密
    public static byte[] decrypt(byte[] data, PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            int inputLen = data.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offset = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段解密
            while (inputLen - offset > 0) {
                if (inputLen - offset > MAX_DECRYPT_BLOCK) {
                    cache = cipher.doFinal(data, offset, MAX_DECRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(data, offset, inputLen - offset);
                }
                out.write(cache, 0, cache.length);
                i++;
                offset = i * MAX_DECRYPT_BLOCK;
            }
            byte[] decryptedData = out.toByteArray();
            out.close();
            return decryptedData;
            // 解密后的内容
        } catch (Exception e) {
            log.error("decrypt error: {}", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


}
