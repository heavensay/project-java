package testjson;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonSetter;

public class Bean2 {
    private int id;
    @JsonProperty("sir")
    private String name;
    private String addr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    @JsonSetter("area")
    public void setAddr(String addr) {
        this.addr = addr;
    }

}
