package com.zmc.configuration;

import java.io.IOException;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.zmc.beandefintion.BeanDefinition;
import com.zmc.beandefintion.PropertyDefinition;


/**
 * @author zhongmc
 * Bean工厂初始化
 */
public class BeanFactoryConfiguration {
	public BeanFactoryConfiguration(String xmlPath,Set<BeanDefinition> beanDefinitions){
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder newDocumentBuilder = dbf.newDocumentBuilder();
			Document doc = newDocumentBuilder.parse(this.getClass().getResourceAsStream(xmlPath));
			parse(doc,beanDefinitions);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//构建BeanDefinition
	private void parse(Document doc, Set<BeanDefinition> beanDefinitions) {
		NodeList beans = doc.getElementsByTagName("bean");
		int beanSize = beans.getLength();
		for (int i = 0; i < beanSize; i++) {
			BeanDefinition beanDefinition = new BeanDefinition();
			Node bean = beans.item(i);
			NamedNodeMap beanAttributes = bean.getAttributes();
			int attrsSize = beanAttributes.getLength();
			for (int j = 0; j < attrsSize; j++) {
				Node attr = beanAttributes.item(j);
				if ("id".equals(attr.getNodeName())) {
					//设置id
					beanDefinition.setId(attr.getNodeValue());
				}else if ("class".equals(attr.getNodeName())) {
					//设置class
					beanDefinition.setClassPath(attr.getNodeValue());
				}else {
					//
				}
			}
			//property标签
			NodeList childNodes = bean.getChildNodes();
			buildProperty(childNodes,beanDefinition.getPropertyDefinitions());
			beanDefinitions.add(beanDefinition);
		}
	}

	private void buildProperty(NodeList childNodes,
			Set<PropertyDefinition> propertyDefinitions) {
		int propertySize = childNodes.getLength();
		for (int i = 0; i < propertySize; i++) {
			Node property = childNodes.item(i);
			if ("property".equals(property.getNodeName())) {
				PropertyDefinition propertyDefinition = new PropertyDefinition();
				NamedNodeMap attributes = property.getAttributes();
				int propertyAttrsSize = attributes.getLength();
				for (int j = 0; j < propertyAttrsSize; j++) {
					Node propertyAttr = attributes.item(j);
					if ("name".equals(propertyAttr.getNodeName())) {
						//设置property的name属性
						propertyDefinition.setName(propertyAttr.getNodeValue());
					}else if ("value".equals(propertyAttr.getNodeName())) {
						propertyDefinition.setValue(propertyAttr.getNodeValue());
					}else{
						//ref
						propertyDefinition.setRef(propertyAttr.getNodeValue());
					}
				}
				propertyDefinitions.add(propertyDefinition);
			}else {
				//非property标签
			}
			
			
		}
	}
}
