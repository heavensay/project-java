package testmail;

import org.junit.*;
import org.junit.Test;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

public class TestCommon {

    @Test
    public void test1() throws Exception {
        String s = "中国";
        String content = new String(s.getBytes("UTF-8"), "iso-8859-1");
        System.out.println(content);
        System.out.println(MimeUtility.encodeText(s, "gb2312", "B"));
    }
}
