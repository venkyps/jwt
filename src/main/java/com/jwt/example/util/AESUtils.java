package com.jwt.example.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class AESUtils {
    private static final String ALGORITHM ="AES";

    public static SecretKey generateKey() throws Exception{
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(128);
        return keyGenerator.generateKey();
    }

    public static byte[] encrypt(byte[] data, SecretKey key) throws Exception{
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE,key);
        return cipher.doFinal(data);
    }

    public static byte[] deCrypt(byte [] data,SecretKey key) throws Exception{
        Cipher cipher= Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE,key);
        return cipher.doFinal(data);
    }
}
