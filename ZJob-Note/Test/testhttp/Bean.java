package testhttp;

import org.codehaus.jackson.map.annotate.JsonRootName;

//@JsonIgnoreProperties({"name"})//name元素不会参数转化
@JsonRootName("ROOT-LEVEL")
public class Bean {
	private int id;
	private String name;
//	@JsonProperty("area") //object->jsonstr，i.e. {"id":5,"name":"tom","area":"深圳福田区"}
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

	public void setAddr(String addr) {
		this.addr = addr;
	}

}
