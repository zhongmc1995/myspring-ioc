package com.zmc.beandefintion;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhongmc
 * 对应xml中的bean标签
 */
public class BeanDefinition {
	private String classPath;//bean的类全限定名
	private String id;//bean的id
	//bean标签内中的property
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
