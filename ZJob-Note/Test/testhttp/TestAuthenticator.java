package testhttp;

import java.nio.charset.Charset;
import java.security.MessageDigest;

import org.apache.catalina.util.MD5Encoder;
import org.junit.Test;

public class TestAuthenticator {
	
	@Test
	public void test1DigestAuthenticator() throws Exception{
		String s = "abc";
		String encoding="UTF-8";
		MessageDigest md5help = MessageDigest.getInstance("MD5");
		MD5Encoder md5Encoder = new MD5Encoder();
		
        String a2 = "GET" + ":" + "/explore/";

        byte[] buffer;
        buffer = md5help.digest(a2.getBytes(Charset.defaultCharset()));
        String md5a2 = md5Encoder.encode(buffer);
        

		// String digestValue = username + ":" + realmName + ":"
		// + getPassword(username);
		String digestValue = "tomcat" + ":" + "Authentication required" + ":" + "tomcat";

		byte[] valueBytes = digestValue.getBytes(Charset.defaultCharset());

		byte[] digest = md5help.digest(valueBytes);

		String md5a1 = md5Encoder.encode(digest);

		// if (qop == null) {
		// serverDigestValue = md5a1 + ":" + nonce + ":" + md5a2;
		// } else {
		// serverDigestValue = md5a1 + ":" + nonce + ":" + nc + ":" +
		// cnonce + ":" + qop + ":" + md5a2;
		// }

		String serverDigestValue = md5a1 + ":" + "1339222725978:521e7c8f2081db9e25a66eea6d80c0d3" + ":" + "00000007" + ":" 
		+ "64e4b82bc57d0e80" + ":"
				+ "auth" + ":" + md5a2;
		String result = md5Encoder.encode(md5help.digest(serverDigestValue
				.getBytes(Charset.defaultCharset())));
		System.out.println(result);
	}
}
