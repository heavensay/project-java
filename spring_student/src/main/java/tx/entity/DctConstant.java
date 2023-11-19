package tx.entity;
// default package

import java.sql.Timestamp;

public class DctConstant {

    private Integer rid;
    private String typecode;
    private String typename;
    private String constcode;
    private String constname;
    private String isvalid;
    private Integer rank;
    private String remark;
    private Timestamp createtime;
    private Timestamp inputtime;
    private Timestamp updatetime;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getTypecode() {
        return typecode;
    }

    public void setTypecode(String typecode) {
        this.typecode = typecode;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getConstcode() {
        return constcode;
    }

    public void setConstcode(String constcode) {
        this.constcode = constcode;
    }

    public String getConstname() {
        return constname;
    }

    public void setConstname(String constname) {
        this.constname = constname;
    }

    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
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