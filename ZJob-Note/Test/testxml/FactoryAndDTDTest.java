package testxml;

import java.io.File;

import junit.framework.TestCase;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;

public class FactoryAndDTDTest extends TestCase {

    public void test01parse() throws Exception {
        parseXml();
    }

    public void parseXml() throws Exception {
        SAXReader reader = new SAXReader(true);
        Document document = reader.read(new File("Test\\testxml\\bean.xml"));
        //  return document;
    }

    public void test02getInstance() {
        ObjectFactory objectFactory = new ObjectFactory();
        Person person = objectFactory.getInstance((Person.class));
        System.out.println(person.getBird());
    }

}
