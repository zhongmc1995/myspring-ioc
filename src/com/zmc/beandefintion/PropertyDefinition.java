package com.zmc.beandefintion;


/**
 * @author zhongmc
 * bean��ǩ���������
 */
public class PropertyDefinition {
	private String name;//��Ӧproperty��ǩ�е�����name
	private String ref;//��Ӧproperty��ǩ�е�����ref����
	private String value;//property��ǩ�ж�Ӧ��value
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
