package com.zmc.beandefintion;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhongmc
 * ��Ӧxml�е�bean��ǩ
 */
public class BeanDefinition {
	private String classPath;//bean����ȫ�޶���
	private String id;//bean��id
	//bean��ǩ���е�property
	private Set<PropertyDefinition> propertyDefinitions = 
			new HashSet<PropertyDefinition>();
	public String getClassPath() {
		return classPath;
	}
	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Set<PropertyDefinition> getPropertyDefinitions() {
		return propertyDefinitions;
	}
	public void setPropertyDefinitions(Set<PropertyDefinition> propertyDefinitions) {
		this.propertyDefinitions = propertyDefinitions;
	}
}
