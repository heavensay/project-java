
package com.cxf.client;

import javax.activation.DataHandler;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Annex complex type�� Java �ࡣ
 *
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 *
 * <pre>
 * &lt;complexType name="Annex">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="annexData" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Annex", propOrder = {
        "annexData",
        "name"
})
public class Annex {

    @XmlMimeType("application/octet-stream")
    protected DataHandler annexData;
    @XmlElementRef(name = "name", type = JAXBElement.class)
    protected JAXBElement<String> name;

    /**
     * ��ȡannexData���Ե�ֵ��
     *
     * @return possible object is
     * {@link DataHandler }
     */
    public DataHandler getAnnexData() {
        return annexData;
    }

    /**
     * ����annexData���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link DataHandler }
     */
    public void setAnnexData(DataHandler value) {
        this.annexData = value;
    }

    /**
     * ��ȡname���Ե�ֵ��
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public JAXBElement<String> getName() {
        return name;
    }

    /**
     * ����name���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public void setName(JAXBElement<String> value) {
        this.name = value;
    }

}
