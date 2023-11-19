package test.common;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.junit.Test;

public class TestOracleJava {
    public static void main(String[] args) throws Exception {
//		byte[] b = URLDecoder.decode("%E4%BC%81%E9%B9%85","utf-8");
        String s = "量化多空";

        System.out.println(System.getProperty("file.encoding"));


        //-17--65--67--17--65--67--17--65--67--17
        //--65--67--17--65--67--17--65--67--17--65--67-46-106-112-103-***
        //63-63-63-63-63-63-63-46-106-112-103-file

        byte[] b1 = new byte[]{-17, -65, -67, -17, -65, -67, -17, -65, -67, -17,
                -65, -67, -17, -65, -67, -17, -65, -67, -17, -65, -67, -46, -106, -112, -103};

        byte[] b2 = new byte[]{63, 63, 63, 63, 63, 63, 63, 46, 106, 112, 103};

        System.out.println(new String(b1));
        System.out.println(new String(b1, "gbk"));
        System.out.println(new String(b1, "utf-8"));

        System.out.println(new String(b2));
        System.out.println(new String(b2, "gbk"));
        System.out.println(new String(b2, "utf-8"));
//		if(f.isDirectory()){
//			File[] files = f.listFiles();
//			for (File file2 : files) {
//				
//				String name = file2.getName();
//				System.out.println("量化多空"+"量化多空".equals(name));
//				for (byte b : name.getBytes()) {
//					System.out.print(b+"-");
//				}
//				System.out.println("***");
//				for (byte b : name.getBytes("GBK")) {
//					System.out.print(b+"-");
//				}
//				System.out.println("file name==:"+name);
//			}
//		}
    }

    @Test
    public void test1() throws Exception {
        String name = "量化多空";
        System.out.println("量化多空" + "量化多空".equals(name));
        for (byte b : name.getBytes()) {
            System.out.print(b + "-");
        }
        System.out.println("***");
        byte[] utfs = new byte[]{-23, -121, -113, -27, -116, -106, -27, -92, -102, -25, -87, -70};
        System.out.println("utf:" + new String(utfs));
        System.out.println("utf:" + new String(utfs, "GBK"));

        for (byte b : name.getBytes("UTF-8")) {
            System.out.print(b + "-");
        }
        System.out.println("***");
        for (byte b : name.getBytes("GBK")) {
            System.out.print(b + "-");
        }
        byte[] gbk = new byte[]{-63, -65, -69, -81, -74, -32, -65, -43};
        System.out.println("utf:" + new String(gbk));
        System.out.println("gbk:" + new String(gbk, "GBK"));

        System.out.println("file name==:" + name);
    }

}
