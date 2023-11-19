package com.myhexin.entity;

import java.sql.Timestamp;

public class PctMetal {

    private Integer id;
    private String pctcode;
    private String pctname;
    private String intro;
    private String purity;
    private String weight;
    private String price;
    private String picpath1;
    private String picpath2;
    private String picpath3;
    private String label;
    private Integer rank;
    private String customlabel;
    private String ext1;
    private String ext2;
    private Timestamp inputtime;
    private Timestamp updatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPctcode() {
        return pctcode;
    }

    public void setPctcode(String pctcode) {
        this.pctcode = pctcode;
    }

    public String getPctname() {
        return pctname;
    }

    public void setPctname(String pctname) {
        this.pctname = pctname;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getPurity() {
        return purity;
    }

    public void setPurity(String purity) {
        this.purity = purity;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPicpath1() {
        return picpath1;
    }

    public void setPicpath1(String picpath1) {
        this.picpath1 = picpath1;
    }

    public String getPicpath2() {
        return picpath2;
    }

    public void setPicpath2(String picpath2) {
        this.picpath2 = picpath2;
    }

    public String getPicpath3() {
        return picpath3;
    }

    public void setPicpath3(String picpath3) {
        this.picpath3 = picpath3;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getCustomlabel() {
        return customlabel;
    }

    public void setCustomlabel(String customlabel) {
        this.customlabel = customlabel;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    public Timestamp getInputtime() {
        return inputtime;
    }

    public void setInputtime(Timestamp inputtime) {
        this.inputtime = inputtime;
    }

    public Timestamp getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Timestamp updatetime) {
        this.updatetime = updatetime;
    }

}