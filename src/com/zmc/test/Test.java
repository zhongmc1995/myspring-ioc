package com.zmc.test;

import com.zmc.factory.XmlBeanFactory;

public class Test {

	public static void main(String[] args) {
		XmlBeanFactory xmlBeanFactory = new XmlBeanFactory("/xml/beans.xml");
		Hello hello1 = (Hello)xmlBeanFactory.getBean("hello");
		hello1.say();
		Hellos hellos = (Hellos)xmlBeanFactory.getBean("hellos");
		hellos.sayHello();
	}

}
