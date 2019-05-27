package com.mark.app.hjshop4a.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;


/**
 * 
 * Description:密码工具类
 * 
 * @author yxx
 * @date 2018年8月21日
 */
public class PasswordUtil {
//	private static final Logger LOGGER = Logger.getLogger(PasswordUtil.class);

	/**
	 * 定义加密方式 支持以下任意一种算法
	 * <p/>
	 * 
	 * <pre>
	 *  
	 * PBEWithMD5AndDES 
	 * PBEWithMD5AndTripleDES 
	 * PBEWithSHA1AndDESede 
	 * PBEWithSHA1AndRC2_40
	 * </pre>
	 */
	private final static String KEY_PBE = "PBEWITHMD5andDES";
	
	/*private final static String KEY = "xiaoming";
	
	private final static String SALT = "xmym2019";*/
	
	private final static String KEY = "baoyunwang";
	
	private final static String SALT = "qsqx2018";
	
	private final static int SALT_COUNT = 20;
	
	
	 /** 
     * 加密解密算法 执行一次加密，两次解密 
     */   
    public static String convertMD5(String inStr){  
  
        char[] a = inStr.toCharArray();  
        for (int i = 0; i < a.length; i++){  
            a[i] = (char) (a[i] ^ 't');  
        }  
        String s = new String(a);  
        return s;  
  
    }  

//	/**
//	 * 方法名: getIpAddress 方法描述: 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址; 修改时间：2017年2月18日
//	 * 上午9:54:54 参数 @param request 参数 @return 参数 @throws IOException 参数说明 返回类型
//	 * String 返回类型
//	 */
//	public final static String getIpAdrress(HttpServletRequest request) {
//		// 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
//		if (null == request) {
//			return "";
//		}
//		String ip = request.getHeader("X-Forwarded-For");
//		if (LOGGER.isInfoEnabled()) {
//			LOGGER.info("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip=" + ip);
//		}
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//	           ip = request.getRemoteAddr();
//	           if(ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")){
//	               //根据网卡获取本机配置的IP地址
//	               InetAddress inetAddress = null;
//	               try {
//	                   inetAddress = InetAddress.getLocalHost();
//	               } catch (UnknownHostException e) {
//	                   e.printStackTrace();
//	               }
//	               ip = inetAddress.getHostAddress();
//	           }
//	       }
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//				ip = request.getHeader("Proxy-Client-IP");
//				if (LOGGER.isInfoEnabled()) {
//					LOGGER.info("getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip=" + ip);
//				}
//			}
//			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//				ip = request.getHeader("WL-Proxy-Client-IP");
//				if (LOGGER.isInfoEnabled()) {
//					LOGGER.info("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip=" + ip);
//				}
//			}
//			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//				ip = request.getHeader("HTTP_CLIENT_IP");
//				if (LOGGER.isInfoEnabled()) {
//					LOGGER.info("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip=" + ip);
//				}
//			}
//			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
//				if (LOGGER.isInfoEnabled()) {
//					LOGGER.info("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip=" + ip);
//				}
//			}
//			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//				ip = request.getRemoteAddr();
//				if (LOGGER.isInfoEnabled()) {
//					LOGGER.info("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip=" + ip);
//				}
//			}
//		} else if (ip.length() > 15) {
//			String[] ips = ip.split(",");
//			for (int index = 0; index < ips.length; index++) {
//				String strIp = ips[index];
//				if (!("unknown".equalsIgnoreCase(strIp))) {
//					ip = strIp;
//					break;
//				}
//			}
//		}
//		return ip;
//	}

	public static String getRandomCapatch(int num) {
		StringBuffer flag = new StringBuffer();
		String sources = "0123456789";
		Random rand = new Random();

		for (int j = 0; j < num; j++) {
			flag.append(sources.charAt(rand.nextInt(9)) + "");
		}
		return flag.toString();
	}

	/**
	 * 生成 加密salt
	 * 
	 * @return
	 */
	public static String genSalt() {
		return genSalt(5);
	}

	/**
	 * 生成 加密salt
	 * 
	 * @param length 长度
	 * @return
	 */
	public static String genSalt(int length) {
		return UUID.randomUUID().toString().substring(0, length);
	}


//	/**
//	 * 登陆验证
//	 *
//	 * @param passStr
//	 * @param passLogin
//	 * @param salt
//	 * @return
//	 */
//	public static boolean validate(String passStr, String passLogin, String salt) {
//
//		return StringUtils.equals(passStr, encrypt(passLogin, salt));
//	}

	/**
	 * 正则表达式验证密码必须要包含数字和大小写字母的6-20位密码
	 * 
	 * @param input
	 * @return
	 */
	public static boolean rexCheckPassword(String input) {
		// 6-20 位，字母、数字、字符
		// String reg = "^([A-Z]|[a-z]|[0-9]|[`-=[];,./~!@#$%^*()_+}{:?]){6,20}$";
		// String regStr = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,20}$";
		// //2017-05-26 18:44 替换
		String regStr = "^([A-Z]|[a-z]|[0-9]|[~!@#$%^&*\\(\\)_+\\{\\}\\|:”<>?`\\[\\]\\-\\\\=;’,./]){6,16}$";
		return input.matches(regStr);
	}

	/**
	 * 初始化盐（salt）
	 * 
	 * @return
	 */
	public static byte[] init() {
		byte[] salt = new byte[8];
		Random random = new Random();
		random.nextBytes(salt);
		return salt;
	}

	/**
	 * 转换密钥
	 * 
	 * @param key 字符串
	 * @return
	 */
	public static Key stringToKey(String key) {
		SecretKey secretKey = null;
		try {
			PBEKeySpec keySpec = new PBEKeySpec(key.toCharArray());
			SecretKeyFactory factory = SecretKeyFactory.getInstance(KEY_PBE);
			secretKey = factory.generateSecret(keySpec);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return secretKey;
	}

	/**
	 * PBE 加密
	 * 
	 * @param data 需要加密的字节数组
	 * @param key  密钥
	 * @param salt 盐
	 * @return
	 */
	public static byte[] encryptPBE(byte[] data, String key, byte[] salt) {
		byte[] bytes = null;
		try {
			// 获取密钥
			Key k = stringToKey(key);
			PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, SALT_COUNT);
			Cipher cipher = Cipher.getInstance(KEY_PBE);
			cipher.init(Cipher.ENCRYPT_MODE, k, parameterSpec);
			bytes = cipher.doFinal(data);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		}
		return bytes;
	}

	/**
	 * PBE 解密
	 * 
	 * @param data 需要解密的字节数组
	 * @param key  密钥
	 * @param salt 盐
	 * @return
	 */
	public static byte[] decryptPBE(byte[] data, String key, byte[] salt) {
		byte[] bytes = null;
		try {
			// 获取密钥
			Key k = stringToKey(key);
			PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, SALT_COUNT);
			Cipher cipher = Cipher.getInstance(KEY_PBE);
			cipher.init(Cipher.DECRYPT_MODE, k, parameterSpec);
			bytes = cipher.doFinal(data);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		}
		return bytes;
	}

	/**
	 * byte[]数组转换十六进制
	 * 
	 * @param key 需要加密的字节数组
	 * @return 字符串
	 * @throws Exception
	 */
	public static String encryptBase64(byte[] bytes) {
		StringBuilder buf = new StringBuilder(bytes.length * 2);
		for (byte b : bytes) { // 使用String的format方法进行转换
			buf.append(String.format("%02x", new Integer(b & 0xff)));
		}

		return buf.toString();
	}

	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] b = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			// 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个字节
			b[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return b;
	}


	// 进行加密
	public static String encryption(String strings) {
		String str = strings; // 需要加密的数据
		// 口令 :密钥
		String key = PasswordUtil.KEY;
		// 初始化盐
		String string = new String(PasswordUtil.SALT);
		byte[] salt = string.getBytes();

		// 采用PBE算法加密
		byte[] encData = encryptPBE(str.getBytes(), key, salt);
		String encStr = encryptBase64(encData);
		return encStr;
	}

	// 进行解密
	public static String decode(String data) {
		// 口令 :密钥
		String key = PasswordUtil.KEY;
		// 初始化盐
		String string = new String(PasswordUtil.SALT);
		byte[] salt = string.getBytes();
		byte[] encData = hexStringToByteArray(data); // 十六进制字符串转byte[]数组
		// 采用PBE算法解密
		byte[] decData = decryptPBE(encData, key, salt);
		String decStr = null;
		try {
			decStr = new String(decData, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decStr;
	}
	

}
