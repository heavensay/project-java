import java.io.File;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

import org.junit.Before;
import org.junit.Test;

import com.cxf.client.Annex;
import com.cxf.client.HelloWorldImpl;
import com.cxf.client.HelloWorldImplService;

public class TestWebservice {

    HelloWorldImpl client = null;

    @Before
    public void before() {
        HelloWorldImplService hw = new HelloWorldImplService();
        client = hw.getHelloWorldImplPort();
    }

    @Test
    public void test1() {

        String s = client.sayHello("go to die");
        System.out.println(s);
    }

    @Test
    public void test2Annex() throws Exception {
        Annex annex = new Annex();
//		annex.setName();

        DataSource ds = new FileDataSource(new File("D:/kk/1.jpg"));
        annex.setAnnexData(new DataHandler(ds));
        client.annexTransportation(annex);

    }

}
