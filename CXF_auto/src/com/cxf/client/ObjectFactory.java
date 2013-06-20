
package com.cxf.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.cxf.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AnnexTransportationResponse_QNAME = new QName("http://service.cxf.com/", "annexTransportationResponse");
    private final static QName _Annex_QNAME = new QName("http://service.cxf.com/", "Annex");
    private final static QName _AnnexTransportation_QNAME = new QName("http://service.cxf.com/", "annexTransportation");
    private final static QName _SayHello_QNAME = new QName("http://service.cxf.com/", "sayHello");
    private final static QName _SayHelloResponse_QNAME = new QName("http://service.cxf.com/", "sayHelloResponse");
    private final static QName _AnnexName_QNAME = new QName("", "name");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.cxf.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AnnexTransportationResponse }
     * 
     */
    public AnnexTransportationResponse createAnnexTransportationResponse() {
        return new AnnexTransportationResponse();
    }

    /**
     * Create an instance of {@link Annex }
     * 
     */
    public Annex createAnnex() {
        return new Annex();
    }

    /**
     * Create an instance of {@link AnnexTransportation }
     * 
     */
    public AnnexTransportation createAnnexTransportation() {
        return new AnnexTransportation();
    }

    /**
     * Create an instance of {@link SayHelloResponse }
     * 
     */
    public SayHelloResponse createSayHelloResponse() {
        return new SayHelloResponse();
    }

    /**
     * Create an instance of {@link SayHello }
     * 
     */
    public SayHello createSayHello() {
        return new SayHello();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnnexTransportationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.cxf.com/", name = "annexTransportationResponse")
    public JAXBElement<AnnexTransportationResponse> createAnnexTransportationResponse(AnnexTransportationResponse value) {
        return new JAXBElement<AnnexTransportationResponse>(_AnnexTransportationResponse_QNAME, AnnexTransportationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Annex }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.cxf.com/", name = "Annex")
    public JAXBElement<Annex> createAnnex(Annex value) {
        return new JAXBElement<Annex>(_Annex_QNAME, Annex.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnnexTransportation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.cxf.com/", name = "annexTransportation")
    public JAXBElement<AnnexTransportation> createAnnexTransportation(AnnexTransportation value) {
        return new JAXBElement<AnnexTransportation>(_AnnexTransportation_QNAME, AnnexTransportation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayHello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.cxf.com/", name = "sayHello")
    public JAXBElement<SayHello> createSayHello(SayHello value) {
        return new JAXBElement<SayHello>(_SayHello_QNAME, SayHello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayHelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.cxf.com/", name = "sayHelloResponse")
    public JAXBElement<SayHelloResponse> createSayHelloResponse(SayHelloResponse value) {
        return new JAXBElement<SayHelloResponse>(_SayHelloResponse_QNAME, SayHelloResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "name", scope = Annex.class)
    public JAXBElement<String> createAnnexName(String value) {
        return new JAXBElement<String>(_AnnexName_QNAME, String.class, Annex.class, value);
    }

}
