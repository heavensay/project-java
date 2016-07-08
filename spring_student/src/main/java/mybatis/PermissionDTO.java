package mybatis;

import java.util.List;

public class PermissionDTO {

	private Integer id;
	private Integer pid;
	private String resourcecode;
	private String resourcename;

	private List<PermissionDTO> list;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getResourcecode() {
		return resourcecode;
	}

	public void setResourcecode(String resourcecode) {
		this.resourcecode = resourcecode;
	}

	public String getResourcename() {
		return resourcename;
	}

	public void setResourcename(String resourcename) {
		this.resourcename = resourcename;
	}

	public List<PermissionDTO> getList() {
		return list;
	}

	public void setList(List<PermissionDTO> list) {
		this.list = list;
	}
}
