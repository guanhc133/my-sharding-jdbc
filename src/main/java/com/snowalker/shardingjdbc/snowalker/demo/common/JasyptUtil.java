package com.snowalker.shardingjdbc.snowalker.demo.common;

import org.jasypt.util.text.BasicTextEncryptor;

public class JasyptUtil {

    /**
     * 加密方法
     * @param salt 盐值
     * @param targetString 待加密字符串
     * @return 密文
     */
    public static String encrypt(String salt, String targetString) {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword(salt);
        return encryptor.encrypt(targetString);
    }

    /**
     * 解密方法
     * @param salt 盐值
     * @param targetString 待解密字符串
     * @return 明文
     */
    public static String decrypt(String salt,String targetString) {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword(salt);
        return encryptor.decrypt(targetString);
    }

    public static void main(String[] args) {
        String salt = "salt";
        String password = "root";
        // 进行加密操作
        String encryptString1 = encrypt(salt, password);
        // 进行解密操作
        String decryptString1 = decrypt(salt, encryptString1);
        // 输出明文和密文
        System.out.println("encryptString1="+encryptString1);
        System.out.println("decryptString1="+decryptString1);
    }
}
