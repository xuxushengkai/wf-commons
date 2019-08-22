package com.wf.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class Md5Utils {

	public static String toMd5(byte[] bytes) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException(ex);
        }
        byte[] digest = md5.digest(bytes);
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            int bt = b & 0xff;
            if (bt < 16) {
                sb.append(0);
            }
            sb.append(Integer.toHexString(bt));
        }
        return sb.toString();
    }

    public static String toPath(String md5) {
        String s1 = md5.substring(0, 3);
        String s2 = md5.substring(3, 6);

        String l1 = String.valueOf(Integer.parseInt(s1, 16) / 4);
        String l2 = String.valueOf(Integer.parseInt(s2, 16) / 4);

        return "/" + l1 + "/" + l2 + "/" + md5 + "/";
    }

    /**
     * @Title:       getMD5
     * @Description: 对字符串进行MD5加密
     * @return:      String
     * @throws
     */
    public static String getMD5(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            log.warn("NoSuchAlgorithmException caught! {}", e.getMessage());
        } catch (UnsupportedEncodingException e) {
            log.warn(e.getMessage());
        }

        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();

        int length = byteArray.length;

        for (int i = 0; i < length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1){
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            }
            else{
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }
        return md5StrBuff.toString();
    }
    /**
     *
     * @Title:        getMd5
     * @Description:  计算文件Md5值
     * @param:        file
     * @param:        @return
     * @return:       String
     * @throws
     * @author        FrankWong
     */
    public static String getMd5(File file){
        String md5 = file.getName();
        try {
            md5 = DigestUtils.md5Hex(FileUtils.openInputStream(file));
        } catch (Exception e) {
            md5 = IdGenUtils.uuid();
            log.warn("generate md5 failed,file name: {}", file.getName());
        }
        return md5;
    }
}
