package com.zmc.beandefintion;


/**
 * @author zhongmc
 * bean标签里面的属性
 */
public class PropertyDefinition {
	private String name;//对应property标签中的属性name
	private String ref;//对应property标签中的属性ref引用
	private String value;//property标签中对应的value
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
