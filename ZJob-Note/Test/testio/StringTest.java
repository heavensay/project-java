package testio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;


import junit.framework.TestCase;

public class StringTest extends TestCase {
	public void test1append(){
		StringBuffer sb = new StringBuffer();
		sb.append("\\");
		sb.append("a");
		sb.append("\\");
		sb.append("b");
		System.out.println(sb.toString());
	}
	public void test2newline(){
		String s = "a"+"\r\n"+"b";
		String s2 = "c\r\nd";
		System.out.println(s);
		System.out.println(s2);
	}
	public void test3() throws Exception{
		String text = "C:\\Documents and Settings\\Administrator\\桌面\\111.html";
		FileOutputStream out = null;
		FileInputStream in = null;
		try{
		in = new FileInputStream(text);
		InputStreamReader streamReader = new InputStreamReader(in, "GB18030"); 
		StringBuffer sb = new StringBuffer();
		int cc = in.read();
		while(cc!=-1){
			sb.append((char)cc);
			cc = in.read();
		}
		String s = sb.toString();
		System.out.print(sb.toString());
		System.out.println(s.getBytes(""));
		out = new FileOutputStream("C:\\Documents and Settings\\Administrator\\桌面\\222.html");
		out.write(s.getBytes());
		System.out.println(s);
		}finally{
			in.close();
			out.flush();
			out.close();
		}
	}
	// iso-8859-1 能 还原回来 兼容其他字符集
	// gbk 在 编解码时会丢失 
	public void test30() throws Exception{
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
		
	public void test60(){
		int a  = 0;
		int b = 0;
		System.out.println(a++);
		System.out.println(++b);
	}
	public void test61(){
		String s ="ss\r\nadfa";
		System.out.println(s);
		
	}
}
