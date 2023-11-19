package testjson;

import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;

public class FundBean {

    private List<FundItem> data;

    @JsonProperty("error")
    private Map map;

    public List<FundItem> getData() {
        return data;
    }

    public void setData(List<FundItem> data) {
        this.data = data;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
