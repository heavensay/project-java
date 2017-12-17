package testlog;

import java.io.PrintStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 系统输出流、错误流重定向到日志框架中
 * 
 * @see http://seeallsea.iteye.com/blog/2117458
 * @author internet
 *
 */
public class StdOutErrRedirect {

	private static Logger logger = LoggerFactory.getLogger(StdOutErrRedirect.class);

	/**
	 * 在程序入口处执行，全局生效
	 */
	public static void redirectSystemOutAndErrToLog() {
		PrintStream printStreamForOut = createLoggingWrapper(System.out, false);
		PrintStream printStreamForErr = createLoggingWrapper(System.err, true);
		System.setOut(printStreamForOut);
		System.setErr(printStreamForErr);
	}

	public static PrintStream createLoggingWrapper(
			final PrintStream printStream, final boolean isErr) {
		return new PrintStream(printStream) {
			@Override
			public void print(final String string) {
				log(string);
			}

			@Override
			public void print(boolean b) {
				log(String.valueOf(b));
			}

			@Override
			public void print(char c) {
				log(String.valueOf(c));
			}

			@Override
			public void print(int i) {
				log(String.valueOf(i));
			}

			@Override
			public void print(long l) {
				log(String.valueOf(l));
			}

			@Override
			public void print(float f) {
				log(String.valueOf(f));
			}

			@Override
			public void print(double d) {
				log(String.valueOf(d));
			}

			@Override
			public void print(char[] x) {
				log(x == null ? null : new String(x));
			}

			@Override
			public void print(Object obj) {
				log(String.valueOf(obj));
			}
		
			@Override
			public void println() {
				log(null);
			}
			
			@Override
			public void println(final String string) {
				log(string);
			}

			@Override
			public void println(boolean b) {
				log(String.valueOf(b));
			}

			@Override
			public void println(char c) {
				log(String.valueOf(c));
			}

			@Override
			public void println(int i) {
				log(String.valueOf(i));
			}

			@Override
			public void println(long l) {
				log(String.valueOf(l));
			}

			@Override
			public void println(float f) {
				log(String.valueOf(f));
			}

			@Override
			public void println(double d) {
				log(String.valueOf(d));
			}

			@Override
			public void println(char[] x) {
				log(x == null ? null : new String(x));
			}

			@Override
			public void println(Object obj) {
				log(String.valueOf(obj));
			}			
			
			@Override
			public void write(byte[] buf, int off, int len) {
				String str = new String(buf,off,len); 
				log(str);
			}
			
			@Override
			public void write(int b) {
				log(String.valueOf(b));
			}
			
			
			/**
			 * 不同日志级别输出内容
			 * @param info
			 */
			private void log(String info){
				if (!isErr) {
					logger.debug(info);
				} else {
					logger.error(info);
				}
			}
		};
	}
}
