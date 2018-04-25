package test.syntax.tenum;

public enum KeyValueEnum  {

	KV1(1,"tom"),KV2(2,"jack"),KV3;
	
	
	public Integer key ;
	public String value ;

	KeyValueEnum(){
		
	}

	KeyValueEnum(Integer key,String value){
		this.key = key;
		this.value = value;
	}

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}

