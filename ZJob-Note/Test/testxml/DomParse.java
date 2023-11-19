package testxml;

import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomParse {
    public DomParse() {

        DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dombuilder = domfac.newDocumentBuilder();

            InputStream is = new FileInputStream("Test\\testxml\\person.xml");
            Document doc = dombuilder.parse(is);
            Element root = doc.getDocumentElement();
            NodeList persons = root.getChildNodes();
            if (persons != null) {
                for (int i = 0; i < persons.getLength(); i++) {
                    Node person = persons.item(i);
                    if (person.getNodeType() == Node.ELEMENT_NODE) {
                        String e = ((Element) person).getAttribute("email");
                        String email = person.getAttributes().getNamedItem(
                                "email").getNodeValue();
                        System.out.println(email);
                        for (Node node = person.getFirstChild(); node != null; node = node
                                .getNextSibling()) {
                            if (node.getNodeType() == Node.ELEMENT_NODE) {
                                if (node.getNodeName().equals("name")) {
                                    String name = node.getNodeValue();
                                    String name1 = node.getFirstChild()
                                            .getNodeValue();
                                    //		　　String name=node.getNodeValue();　 是一个空值。而
                                    //		　　String name1=node.getFirstChild().getNodeValue();　
                                    //		才是真正的值，这是因为DOM把<name>rjzjh</name>也当作是两层结构的节点，其父节点是<name>，子节点rjzjh才是我们真正想得到的。
                                    System.out.println(name);
                                    System.out.println(name1);
                                }
                                if (node.getNodeName().equals("price")) {

                                    String price = node.getFirstChild()
                                            .getNodeValue();
                                    System.out.println(price);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new DomParse();
    }
}