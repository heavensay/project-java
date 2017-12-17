package test.security;

import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;

public class KeyStoreCoder {

	/**
	 * Java密钥库(Java Key Store，JKS)KEY_STORE
	 */
	public static final String KEY_STORE = "JKS";

	public static final String X509 = "X.509";

	/**
	 * 获得KeyStore
	 * 
	 * @author 奔跑的蜗牛
	 * @version 2012-3-16
	 * @param keyStorePath
	 * @param password
	 * @return
	 * @throws Exception
	 */
	private static KeyStore getKeyStore(String keyStorePath, String password)
			throws Exception {

		FileInputStream is = new FileInputStream(keyStorePath);
		KeyStore ks = KeyStore.getInstance(KEY_STORE);
		ks.load(is, password.toCharArray());
		is.close();
		return ks;
	}

	/**
	 * 由KeyStore获得私钥
	 * 
	 * @author 奔跑的蜗牛
	 * @param keyStorePath
	 * @param alias
	 * @param storePass
	 * @return
	 * @throws Exception
	 */
	private static PrivateKey getPrivateKey(String keyStorePath, String alias,
			String storePass, String keyPass) throws Exception {
		KeyStore ks = getKeyStore(keyStorePath, storePass);
		PrivateKey key = (PrivateKey) ks.getKey(alias, keyPass.toCharArray());
		return key;
	}

	/**
	 * 由Certificate获得公钥
	 * 
	 * @author 奔跑的蜗牛
	 * @param keyStorePath
	 *            KeyStore路径
	 * @param alias
	 *            别名
	 * @param storePass
	 *            KeyStore访问密码
	 * @return
	 * @throws Exception
	 */
	public static PublicKey getPublicKey(String keyStorePath, String alias,
			String storePass) throws Exception {
		KeyStore ks = getKeyStore(keyStorePath, storePass);
		PublicKey key = ks.getCertificate(alias).getPublicKey();
		return key;
	}

	/**
	 * 从KeyStore中获取公钥，并经BASE64编码
	 * 
	 * @author 奔跑的蜗牛
	 * @param keyStorePath
	 * @param alias
	 * @param storePass
	 * @return
	 * @throws Exception
	 */
	public static String getStrPublicKey(String keyStorePath, String alias,
			String storePass) throws Exception {
		PublicKey key = getPublicKey(keyStorePath, alias, storePass);
		String strKey = (new BASE64Encoder()).encodeBuffer(key.getEncoded());
		return strKey;
	}

	/**
	 * 获取经BASE64编码后的私钥
	 * 
	 * @author 奔跑的蜗牛
	 * @param keyStorePath
	 * @param alias
	 * @param storePass
	 * @param keyPass
	 * @return
	 * @throws Exception
	 */
	public static String getStrPrivateKey(String keyStorePath, String alias,
			String storePass, String keyPass) throws Exception {

		PrivateKey key = getPrivateKey(keyStorePath, alias, storePass, keyPass);
		String strKey = (new BASE64Encoder()).encodeBuffer(key.getEncoded());
		return strKey;
	}

	/**
	 * 使用公钥加密数据
	 * 
	 * @author 奔跑的蜗牛
	 * @param publicKey
	 * @param srcData
	 * @return
	 * @throws Exception
	 */
	public static String encryptByPublicKey(String publicKey, String srcData)
			throws Exception {
		// 解密
		byte[] pk = (new BASE64Decoder()).decodeBuffer(publicKey);
		X509EncodedKeySpec spec = new X509EncodedKeySpec(pk);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		// 获取公钥
		PublicKey pubKey = kf.generatePublic(spec);

		// 对数据加密
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, pubKey);

		byte[] doFinal = cipher.doFinal(srcData.getBytes());
		return (new BASE64Encoder()).encodeBuffer(doFinal);
	}

	/**
	 * 使用私钥解密数据
	 * 
	 * @author 奔跑的蜗牛
	 * @param privateKey
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String descryptByPrivateKey(String privateKey, String data)
			throws Exception {
		// BASE64转码解密私钥
		byte[] pk = (new BASE64Decoder()).decodeBuffer(privateKey);
		// BASE64转码解密密文
		byte[] text = (new BASE64Decoder()).decodeBuffer(data);
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(pk);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		// 获取私钥
		PrivateKey prvKey = kf.generatePrivate(spec);

		// 对数据加密
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, prvKey);

		byte[] doFinal = cipher.doFinal(text);
		return new String(doFinal);
	}
}