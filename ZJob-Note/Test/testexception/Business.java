package testexception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Business {

    private static Logger logger = LoggerFactory.getLogger(Business.class);

    public void a() {
        //do something
        throw new ExceptionA();
        //do something
    }

    /**
     * 捕获异常A，重新抛出异常B：记录异常A的信息，否则问题排查的时候会丢失异常A的信息
     */
    public void sevice1() {
        try {
            a();
        } catch (ExceptionA e) {
            logger.error(e.getMessage(), e);
            throw new ExceptionB();
        }
    }

    /**
     * 捕获异常A，重新抛出异常B(嵌套异常A)：不用记录异常A的信息，直接抛出异常B(A)。如果记录异常A的信息，后续调用方记录异常B的话，会重复记录异常A信息。
     */
    public void sevice2() {
        try {
            a();
        } catch (ExceptionA e) {
//			logger.error(e.getMessage(),e);
            throw new ExceptionB(e);
        }
    }

}
