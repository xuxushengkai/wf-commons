package com.wf.common.utils;

import java.util.Random;
import java.util.UUID;

public class IdGenUtils {

	static SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);

	private static Random r = new Random();

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static long sfId(){
		return idWorker.nextId();
	}

	/**
	 * @throws
	 * @Title: generateSmsCode
	 * @Description: 生成随机短信验证码
	 * @param: length 短信验证码长度
	 * @param: @return
	 * @return: String
	 * @author FrankWong
	 */
	public static synchronized String generateCheckPass(int length) {
		String chars = "012356789";
		char[] rands = new char[length];
		for (int i = 0; i < length; i++) {
			int rand = r.nextInt(9);
			rands[i] = chars.charAt(rand);
		}
		return new String(rands);
	}

	public static synchronized String generateCheckPass2(int length) {
		String chars = "012356789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char[] rands = new char[length];
		for (int i = 0; i < length; i++) {
			int rand = r.nextInt(62);
			rands[i] = chars.charAt(rand);
		}
		return new String(rands);
	}

	public static String genRandomMobile(){
		return "12"+generateCheckPass(9);
	}

	public static void main(String[] args) {
		System.out.println(genRandomMobile());
	}
}
