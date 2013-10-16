package testio.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import sun.nio.ch.DirectBuffer;

import junit.framework.TestCase;

public class TestNio extends TestCase{
	
	
	
	public void test1() throws Exception{
		FileInputStream in = new FileInputStream("C:\\Documents and Settings\\Administrator\\桌面\\copy.sql");
		FileOutputStream out = new FileOutputStream("C:\\Documents and Settings\\Administrator\\桌面\\copy.txt");
		FileChannel fin = in.getChannel();
		FileChannel fout = out.getChannel();
		//byte[] b  = new byte[10];
		ByteBuffer buffer = ByteBuffer.allocate(10);
		while (true) {
			// clear方法重设缓冲区，使它可以接受读入的数据
			buffer.clear();
			// 从输入通道中将数据读到缓冲区
			int r = fin.read(buffer);
			// read方法返回读取的字节数，可能为零，如果该通道已到达流的末尾，则返回-1
			if (r == -1) {
				break;
			}
			// flip方法让缓冲区可以将新读入的数据写入另一个通道
			buffer.flip();
			// 从输出通道中将数据写入缓冲区
			fout.write(buffer);
		}
	}
	
	public void test2DirectByteBuffer() throws Exception{
		ByteBuffer bb = ByteBuffer.allocateDirect(8);
		bb.put("c".getBytes());
		bb.put("b".getBytes());
		System.out.println(bb.get(1));
	}
}
