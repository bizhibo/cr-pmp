package com.cr.pmp.common.utils;

import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

/**
 * @描述 : 加密解密工具类
 * @创建者：liushengsong @创建时间： 2014-6-6上午8:57:23
 * 
 */
public class SecurityUtil {
	private final static String DES = "DES";
	private final static String AES = "AES";
	private final static String MD5 = "MD5";

	/**
	 * @描述 : 获得数据的MD5加密串
	 * @创建者：liushengsong @创建时间： 2014-5-5下午5:26:16
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String md5(String str) {
		try {
			if (StringUtils.isNotBlank(str)) {
				MessageDigest md = MessageDigest.getInstance(MD5);
				return byteToString(md.digest(str.getBytes()));
			} else {
				return null;
			}
		} catch (Exception e) {
			LogUtil.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * @描述 : byte转换为字符串
	 * @创建者：liushengsong@eztcn.com @创建时间： 2016年3月28日上午3:14:00
	 *
	 * @param bytes
	 * @return
	 */
	private static String byteToString(byte[] bytes) {
		StringBuffer bf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if ((bytes[i] & 0xff) < 0x10) {
				bf.append("T0");
			}
			bf.append(Long.toString(bytes[i] & 0xff, 16));
		}
		return bf.toString();
	}

	/**
	 * @描述 : AES加密
	 * @创建者：liushengsong@eztcn.com @创建时间： 2016年3月28日上午3:06:46
	 *
	 * @param content
	 *            内容
	 * @param secret
	 *            密钥
	 * @return
	 */
	public static String encryptAES(String content, String secret) {
		try {
			if (StringUtils.isNotBlank(content)
					&& StringUtils.isNotBlank(secret)) {
				KeyGenerator kgen = KeyGenerator.getInstance(AES);
				kgen.init(128, new SecureRandom(secret.getBytes()));
				SecretKey secretKey = kgen.generateKey();
				byte[] enCodeFormat = secretKey.getEncoded();
				SecretKeySpec key = new SecretKeySpec(enCodeFormat, AES);
				Cipher cipher = Cipher.getInstance(AES);// 创建密码器
				byte[] byteContent = content.getBytes();
				cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
				byte[] result = cipher.doFinal(byteContent);
				return Base64.encodeBase64String(result); // 加密
			} else {
				return null;
			}
		} catch (Exception e) {
			LogUtil.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * @描述 : AES解密
	 * @创建者：liushengsong@eztcn.com @创建时间： 2016年3月28日上午3:07:06
	 *
	 * @param content
	 *            内容
	 * @param secret
	 *            密钥
	 * @return
	 */
	public static String decryptAES(String content, String secret) {
		try {
			if (StringUtils.isNotBlank(content)
					&& StringUtils.isNotBlank(secret)) {
				KeyGenerator kgen = KeyGenerator.getInstance(AES);
				kgen.init(128, new SecureRandom(secret.getBytes()));
				SecretKey secretKey = kgen.generateKey();
				byte[] enCodeFormat = secretKey.getEncoded();
				SecretKeySpec key = new SecretKeySpec(enCodeFormat, AES);
				Cipher cipher = Cipher.getInstance(AES);// 创建密码器
				cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
				byte[] result = cipher.doFinal(Base64.decodeBase64(content
						.getBytes()));
				return new String(result); // 解密
			} else {
				return null;
			}
		} catch (Exception e) {
			LogUtil.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * @描述 : DES加密
	 * @创建者：liushengsong@eztcn.com @创建时间： 2016年3月28日上午3:08:23
	 *
	 * @param content
	 *            内容
	 * @param secret
	 *            密钥
	 * @return
	 */
	public static String encryptDES(String content, String secret) {
		try {
			if (StringUtils.isNotBlank(content)
					&& StringUtils.isNotBlank(secret)) {
				SecureRandom random = new SecureRandom();
				DESKeySpec desKey = new DESKeySpec(secret.getBytes());
				SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
				SecretKey securekey = keyFactory.generateSecret(desKey);
				Cipher cipher = Cipher.getInstance(DES);// 创建密码器
				byte[] byteContent = content.getBytes();
				cipher.init(Cipher.ENCRYPT_MODE, securekey, random);// 初始化
				byte[] result = cipher.doFinal(byteContent);
				return Base64.encodeBase64String(result); // 加密
			} else {
				return null;
			}
		} catch (Exception e) {
			LogUtil.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * @描述 : DES解密
	 * @创建者：liushengsong@eztcn.com @创建时间： 2016年3月28日上午3:07:29
	 *
	 * @param content
	 *            内容
	 * @param secret
	 *            密钥
	 * @return
	 */
	public static String decryptDES(String content, String secret) {
		try {
			if (StringUtils.isNotBlank(content)
					&& StringUtils.isNotBlank(secret)) {
				SecureRandom random = new SecureRandom();
				DESKeySpec desKey = new DESKeySpec(secret.getBytes());
				SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
				SecretKey securekey = keyFactory.generateSecret(desKey);
				Cipher cipher = Cipher.getInstance(DES);// 创建密码器
				cipher.init(Cipher.DECRYPT_MODE, securekey, random);// 初始化
				byte[] result = cipher.doFinal(Base64.decodeBase64(content
						.getBytes()));
				return new String(result); // 解密
			} else {
				return null;
			}
		} catch (Exception e) {
			LogUtil.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

}
