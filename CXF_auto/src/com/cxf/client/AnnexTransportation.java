
package com.cxf.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>annexTransportation complex type�� Java �ࡣ
 *
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 *
 * <pre>
 * &lt;complexType name="annexTransportation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://service.cxf.com/}Annex" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "annexTransportation", propOrder = {
        "arg0"
})
public class AnnexTransportation {

    protected Annex arg0;

    /**
     * ��ȡarg0���Ե�ֵ��
     *
     * @return possible object is
     * {@link Annex }
     */
    public Annex getArg0() {
        return arg0;
    }

    /**
     * ����arg0���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link Annex }
     */
    public void setArg0(Annex value) {
        this.arg0 = value;
    }

}
