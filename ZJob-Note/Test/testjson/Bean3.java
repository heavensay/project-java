package testjson;

import java.util.Map;

import org.codehaus.jackson.annotate.JsonGetter;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonSetter;
import org.codehaus.jackson.map.annotate.JsonRootName;

public class Bean3 {
    private int id;
    private Map map;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
