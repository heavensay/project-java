package com.cxf.service;

import java.util.List;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;


@XmlRootElement(name = "Annex")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Annex")
public class Annex {

    @XmlMimeType("application/octet-stream")
    private DataHandler annexData;

    @XmlElement
    private String name;

    private List<String> paths;

    public DataHandler getAnnexData() {
        return annexData;
    }

    public void setAnnexData(DataHandler annexData) {
        this.annexData = annexData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPaths() {
        return paths;
    }

    public void setPaths(List<String> paths) {
        this.paths = paths;
    }

}
