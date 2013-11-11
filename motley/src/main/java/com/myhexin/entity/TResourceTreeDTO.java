package com.myhexin.entity;

import java.util.ArrayList;
import java.util.List;

public class TResourceTreeDTO {

    private Integer               id;
    private String                text;
    private String                state    = "open";
    private boolean               checked;
    private Object attributes;
    private List<TResourceTreeDTO> children;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public List<TResourceTreeDTO> getChildren() {
		return children;
	}
	public void setChildren(List<TResourceTreeDTO> children) {
		this.children = children;
	}
	public Object getAttributes() {
		return attributes;
	}
	public void setAttributes(Object attributes) {
		this.attributes = attributes;
	}
}
