package test.common;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.junit.Test;


import junit.framework.TestCase;

/**
 * utf-8转gbk
 * 乱码等的测试 
 * @author ljy
 *
 */
public class TestEncode {
	

	/**
	 * 汉字的各种编码
	 * 	汉字 unicode编码：0x6c49和0x5b57；UTF-8编码：{0xE6, 0xB1, 0x89, 0xE5, 0xAD, 0x97}
	 * 汉字 gbk编码:{0xba,0xba,0xd7,0xd6}
	 * 把有符号的整数，转为无符号整数：byte&0xff 
	 * 
	 * @throws Exception
	 */
	@Test
	public void test1encode() throws Exception{
		
		System.out.println((int)'汉');
		System.out.println(Integer.toBinaryString((int)'汉'));
		System.out.println(Integer.toHexString((int)'汉'));
		
		String str = "中国";
		
		byte[] unicodes = str.getBytes("unicode");
		System.out.println("以unicode编码 ，长度："+unicodes.length);
		printFormatByteArray(unicodes);
		System.out.print("\r\n fe ff :BOM(Byte Order Mark)的不可见字符，用来标识字节序 （Big/Little Endian）");
		
		byte[] gbks = str.getBytes("gbk");
		System.out.println("以gbk编码 ，长度："+gbks.length);
		printFormatByteArray(gbks);
		
		byte[] iso8859s = str.getBytes("ISO-8859-1");
		System.out.println(new String(str.getBytes("ISO-8859-1"),"GBK"));
		System.out.println("\r\n以iso-8859-1编码 ，长度："+iso8859s.length);
		printFormatByteArray(iso8859s);
		System.out.println(str+":0x6c49,0x5b57  unicode->iso-8859-1转码 "+"--->"+"3f,3f");
		
		byte[] utfs = str.getBytes("utf-8");
		System.out.println("\r\n以utf-8编码 ，长度："+utfs.length);
		printFormatByteArray(utfs);
		
		byte[] utf16s = str.getBytes("utf-16");
		System.out.println("\r\n以utf-16编码 ，长度："+utf16s.length);
		printFormatByteArray(utf16s);
	}
	
	/**
	 * 用gbk编码，utf-8解码：字节不可逆转
	 * result：字符串的byte[]
	 * @throws Exception
	 */
	@Test
	public void test2gibberish() throws Exception{
		String str = "汉字";
		byte[] gbks = str.getBytes("gbk");
		System.out.println("以gbk编码 ，长度："+gbks.length);
		printFormatByteArray(gbks);
		
		
		String gbk2utf8 = new String(str.getBytes("gbk"),"utf-8");
		String tmp = new String(gbk2utf8.getBytes("utf-8"),"gbk");
		
		System.out.println("gbk编码，utf-8解码后的字符串："+gbk2utf8);
		System.out.println("gbk编码，utf-8解码后,在用utf-8编码，在用gbk解码后的字符串："+tmp);
		System.out.println(new String(gbk2utf8.getBytes("utf-8"),"utf-8"));
		
		byte[] gbk2utf8s = new String(str.getBytes("gbk"),"utf-8").getBytes("utf-8");
		System.out.println("gbk编码，utf-8解码，utf-8编码，长度： "+gbk2utf8s.length);
		printFormatByteArray(gbk2utf8s);
	}
	
	
	/**
	 * 为16进制、二进制、整数形式打印byte
	 * @param bytes
	 */

	private void printFormatByteArray(byte[] bytes){
		System.out.print("16进制表示：\r\n");
		for(byte b : bytes){
			System.out.print(Integer.toHexString(b&0xff)+" ");

		}
		
		System.out.print("\r\n无符号byte的整数值：\r\n");
		for(byte b : bytes){
			System.out.print((b&0xff)+" ");
		}
		System.out.print("\r\n有符号byte的整数值：\r\n");
		for(byte b : bytes){
			System.out.print((b)+" ");
		}
				
		System.out.print("\r\n 二进制表现形式：\r\n");
		for(byte b : bytes){
			System.out.print(Integer.toBinaryString(b&0xff)+" ");
		}
		System.out.println("\r\n------------------------------");
	}
	
	// iso-8859-1 能 还原回来 兼容其他字符集
	// gbk 在 编解码时会丢失 
	@Test
	public void test3() throws Exception{
		//此文件里面编码格式为UTF-8
		String text = "C:\\Documents and Settings\\Administrator\\桌面\\444.html";
		FileInputStream in = null;
		FileInputStream in2 = null;
		try{
		in = new FileInputStream(text);
		in2 = new FileInputStream(text);
		
		String iso = difEncode(in, "ISO-8859-1");
		String gbk = difEncode(in2, "GBK");
		System.out.println(iso);
		System.out.println(gbk);
		}finally{
			in.close();
			in2.close();
		}
	}
	
	/**
	 * @param in
	 * @param encoding 采用此encoding 编码 数据
	 * @return
	 * @throws Exception
	 */
	public String difEncode(FileInputStream in,String encoding) throws Exception{
		InputStreamReader streamReader = null;
		try{
		streamReader = new InputStreamReader(in, encoding);
		int cc = streamReader.read();
		StringBuffer sb = new StringBuffer();
		while(cc!=-1){
			
			sb.append((char)cc);
			cc = streamReader.read();
		}
		String s = sb.toString();
		String s2 = new String(s.getBytes(encoding),"UTF-8"); //源文件字符为utf-8
		return s2;
		}finally{
			streamReader.close();
		}
	}
	
	// the source text: //
	@Test
	public void test4() throws Exception{
		String text = "c:\\3\\utfaaa.txt";
		FileOutputStream out = null;
		FileInputStream in = null;
		try{
		in = new FileInputStream(text);
		StringBuffer sb = new StringBuffer();
		
		byte[] b = new byte[1024];
		
		int cc = in.read(b);
		while(cc!=-1){
			sb.append((char)cc);
			cc = in.read();
		}
		
		//String utf8String = IOUtils.toString(IOUtils.toInputStream(sb.toString(), "UTF-8"));
		
		out = new FileOutputStream("c:\\3\\ppp.html");
		//out.write(utf8String.getBytes());
		//System.out.println(utf8String);
		}finally{
			out.flush();
			out.close();
		}
	}
	/*
	 *  gbk 编码转换为 utf-8
	 * */
	@Test
	public void test5gbk2utf8() throws Exception{
		FileInputStream in = null;
		FileOutputStream out = null;
		try{
			in = new FileInputStream("C:\\Documents and Settings\\Administrator\\桌面\\111.html");
			byte[] b = gbk2utf8(in);
			
			out = new FileOutputStream("C:\\Documents and Settings\\Administrator\\桌面\\222.html");
			out.write(b);
		}finally{
			in.close();
			out.close();
		}
	}

	//UCS-2编码(16进制)	UTF-8 字节流(二进制)
	//0000 - 007F	0xxxxxxx
	//0080 - 07FF	110xxxxx 10xxxxxx
	//0800 - FFFF	1110xxxx 10xxxxxx 10xxxxxx
	//0000 - 007F:  0 - 127
	//0080 - 07FF:  128 - 2047
	//0800 - FFFF:  2048 - 65535
	@Test
	public void test6range() throws Exception{
		System.out.println("0000 - 007F:  "+Integer.parseInt("0000", 16)+" - "+Integer.parseInt("007F", 16));
		System.out.println("0080 - 07FF:  "+Integer.parseInt("0080", 16)+" - "+Integer.parseInt("07FF", 16));
		System.out.println("0800 - FFFF:  "+Integer.parseInt("0800", 16)+" - "+Integer.parseInt("FFFF", 16));
		System.out.println((int)'中');
		System.out.println("中".getBytes("GBk").length+" - "+Integer.toBinaryString((int)'中'));
		System.out.println("中".getBytes("UTF-8").length+" - "+Integer.toBinaryString((int)'中'));
		//System.out.println(Integer.parseInt(String.valueOf("中"), 16));
	}
	
	public byte[] gbk2utf8(FileInputStream in) throws Exception {
		StringBuffer ss = new StringBuffer();
		
		byte[] bb = new byte[1024];
		int temp = in.read(bb);
		byte[] bb2 = new byte[temp];
		System.arraycopy(bb, 0, bb2, 0, temp);
		int bcount = -1;
		String chenese = new String(bb2,"gbk");
//		String chenese = new String(bb2,"ISO-8859-1");   就会出错
		
		char c[] = chenese.toCharArray();
		byte[] fullByte = new byte[3 * c.length];
		// the index of byte arrty 
		int position = -1;
		for (int i = 0; i < c.length; i++) {
			int m = (int) c[i];
			String word = Integer.toBinaryString(m);
			if (m >= 0 && m <= 127) {
				position = encodeOneByte(word, fullByte, position);
			} else if (m >= 128 && m <= 2047) {
				position = encodeTwoByte(word, fullByte, position);
			} else if (m >= 2048 && m <= 65535) {
				position = encodeThreeByte(word, fullByte, position);
			}
		}
		byte[] dest = new byte[++position];
		System.arraycopy(fullByte, 0, dest, 0, position);
		return dest;
	}
	
	/**
	 * the src is ascii
	 * @param src
	 * @param b
	 * @param position
	 * @return
	 */
	public int encodeOneByte(String src,byte[] b,int position){
		StringBuffer sb = new StringBuffer();
		sb.append("0"+src);
		byte one = Integer.valueOf(sb.toString(), 2).byteValue();
		b[++position] = one;
		return position;
	}
	/**
	 * 为2个字节
	 */
	public int encodeTwoByte(String src,byte[] b,int position){
		StringBuffer sb = new StringBuffer();
		//长度为16 = 11 + 5
		// 110xxxxx 10xxxxxx
		int len = 11 - src.length();
		for (int j = 0; j < len; j++) {
			sb.append("0");
		}
			sb.append(src);
			sb.insert(0, "110");
			sb.insert(8, "10");
			String s1 = sb.substring(0, 8);
			String s2 = sb.substring(8, 16);
			byte b0 = Integer.valueOf(s1, 2).byteValue();
			byte b1 = Integer.valueOf(s2, 2).byteValue();
			b[++position] = b0;
			b[++position] = b1;
		return position;
	}
	// 处理3个字节
	public int encodeThreeByte(String src,byte[] b,int position){
		StringBuffer sb = new StringBuffer();
		int len = 16 - src.length();
		
		for (int j = 0; j < len; j++) {
			sb.append("0");
		}
			sb.append(src);
			sb.insert(0, "1110");
			sb.insert(8, "10");
			sb.insert(16, "10");
			String s1 = sb.substring(0, 8);
			String s2 = sb.substring(8, 16);
			String s3 = sb.substring(16);
			byte b0 = Integer.valueOf(s1, 2).byteValue();
			byte b1 = Integer.valueOf(s2, 2).byteValue();
			byte b2 = Integer.valueOf(s3, 2).byteValue();
			b[++position] = b0;
			b[++position] = b1;
			b[++position] = b2;
			return position;
	}
	
	@Test
	public void test7linuxFileNameBychina() throws Exception{
		
	}
	
}
