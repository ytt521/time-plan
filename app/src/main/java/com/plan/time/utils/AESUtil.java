package com.plan.time.utils;

import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.util.Base64;

public class AESUtil {
    /**
     * 密钥长度: 128, 192 or 256
     */
    private static final int KEY_SIZE = 128;

    /**
     * 加密/解密算法名称
     */
    private static final String ALGORITHM = "AES";

    /**
     * 随机数生成器（RNG）算法名称
     */
    private static final String RNG_ALGORITHM = "SHA1PRNG";

    /**
     * 生成密钥对象
     */
    private static SecretKey generateKey(byte[] key) throws Exception {
        // 创建安全随机数生成器
        SecureRandom random = SecureRandom.getInstance(RNG_ALGORITHM);
        // 设置 密钥key的字节数组 作为安全随机数生成器的种子
        random.setSeed(key);

        // 创建 AES算法生成器
        KeyGenerator gen = KeyGenerator.getInstance(ALGORITHM);
        // 初始化算法生成器
        gen.init(KEY_SIZE, random);

        // 生成 AES密钥对象, 也可以直接创建密钥对象: return new SecretKeySpec(key, ALGORITHM);
        return gen.generateKey();
    }

    /**
     * 数据加密: 明文 -> 密文
     */
    public static String encrypt(String content, byte[] key) throws Exception {
        // 生成密钥对象
        SecretKey secKey;
        try {
            secKey = generateKey(key);
            // 获取 AES 密码器
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            // 初始化密码器（加密模型）
            cipher.init(Cipher.ENCRYPT_MODE, secKey);
            // 加密数据, 返回密文
            byte[] result = new byte[]{};
            if(!StringUtils.isEmpty(content)){
                byte[]  plainBytes = content.getBytes("utf-8");
                result = cipher.doFinal(plainBytes);
            }
            return Base64.getEncoder().encodeToString(result);// 通过Base64转码返回
        } catch (Exception e) {
            throw new Exception( "AES 加密失败:" + e.getMessage());
        }

    }

    /**
     * 数据解密: 密文 -> 明文
     */
    public static String decrypt(String content, byte[] key) throws Exception {
        try {
            // 生成密钥对象
            SecretKey secKey = generateKey(key);

            // 获取 AES 密码器
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            // 初始化密码器（解密模型）
            cipher.init(Cipher.DECRYPT_MODE, secKey);

            // 解密数据, 返回明文
            byte[] result = new byte[]{};
            if(!StringUtils.isEmpty(content)){
                result = cipher.doFinal(Base64.getDecoder().decode(content));
            }

            return new String(result, "utf-8");
        } catch (Exception e) {
            throw new Exception("AES 解密失败:" + e.getMessage());
        }
    }
}

