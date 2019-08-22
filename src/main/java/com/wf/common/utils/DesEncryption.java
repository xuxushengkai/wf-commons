package com.wf.common.utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Slf4j
public class DesEncryption {


    public static String generatePassword(String userId, String password) {
        try {
            String key = Md5Utils.getMD5(userId).substring(0, 8).toUpperCase();
            String iv = Md5Utils.getMD5(userId).substring(0, 8).toUpperCase();
            String psword = encryptDESHexString(password, key, iv);
            System.out.println("password: " + psword);
            return psword;
        } catch (Exception e) {
            log.error("", e);
        }
        return "";
    }

    public static String decryptPassword(String userId, String password) {
        try {
            String key = Md5Utils.getMD5(userId).substring(0, 8).toUpperCase();
            String iv = Md5Utils.getMD5(userId).substring(0, 8).toUpperCase();

            return decryptDES(password, key, iv);
        } catch (Exception e) {
            log.error("", e);
        }
        return "";
    }

    private static String encryptDESString(String encryptString, String key, String iv)
            throws Exception {
        byte[] encryptedData = encryptDES(encryptString, key, iv);
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    private static String encryptDESHexString(String encryptString, String key, String iv)
            throws Exception {
        byte[] encryptedData = encryptDES(encryptString, key, iv);
        return bytesToHex(encryptedData);
    }

    private static byte[] encryptDES(String encryptString, String key, String iv)
            throws Exception {
        IvParameterSpec zeroIv = new IvParameterSpec(iv.getBytes());
        SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skey, zeroIv);
        return cipher.doFinal(encryptString.getBytes());
    }

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }


    public static void main(String[] args) {
        try {
            String temp = generatePassword("2e26c0f10efe11e9918c7cd30adab904", "123456");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String decryptDES(String decryptString, String key, String iv)
            throws Exception {
        byte[] byteMi = hexStringToByteArray(decryptString);
        IvParameterSpec zeroIv = new IvParameterSpec(iv.getBytes());
        SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, skey, zeroIv);
        byte decryptedData[] = cipher.doFinal(byteMi);
        return new String(decryptedData);
    }
}
