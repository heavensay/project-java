package testjson;

import org.codehaus.jackson.map.annotate.JsonRootName;

@JsonRootName("data")
public class FundItem {
    private String code;
    private String date;
    private Double buy;
    private String name;
    private String orgid;
    private Double net;
    private Double totalnet;
    private String date1;
    private Double net1;
    private Double totalnet1;
    private Double range;
    private Double rate;
    private String mark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getBuy() {
        return buy;
    }

    public void setBuy(Double buy) {
        this.buy = buy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public Double getNet() {
        return net;
    }

    public void setNet(Double net) {
        this.net = net;
    }

    public Double getTotalnet() {
        return totalnet;
    }

    public void setTotalnet(Double totalnet) {
        this.totalnet = totalnet;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public Double getNet1() {
        return net1;
    }

    public void setNet1(Double net1) {
        this.net1 = net1;
    }

    public Double getTotalnet1() {
        return totalnet1;
    }

    public void setTotalnet1(Double totalnet1) {
        this.totalnet1 = totalnet1;
    }

    public Double getRange() {
        return range;
    }

    public void setRange(Double range) {
        this.range = range;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
