package com.zmc.factory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.zmc.beandefintion.BeanDefinition;
import com.zmc.beandefintion.PropertyDefinition;
import com.zmc.configuration.BeanFactoryConfiguration;

/**
 * @author zhongmc
 * 基于xml配置的bean工厂
 */
public class XmlBeanFactory implements BeanFactory {
	//缓存bean的容器
	private Map<String, Object> beans = new HashMap<String, Object>();
	//存放beandefinition的容器
	private Set<BeanDefinition> beanDefinitions = new HashSet<BeanDefinition>();
	
	//无参构造函数
	public XmlBeanFactory(String xmlPath){
		new BeanFactoryConfiguration(xmlPath, beanDefinitions);
	}
	
	@Override
	public Object getBean(String id) {
		//缓存中有的话从缓存中获取
		if (beans.get(id)!=null) {
			return beans.get(id);
		}
		Object bean = null;
		BeanDefinition beanDefinition = getBeanDefinition(id);
		if (beanDefinition!=null) {
			String classPath = beanDefinition.getClassPath();
			try {
				Class<?> className = Class.forName(classPath);
				bean = className.newInstance();
				for (PropertyDefinition propertyDefinition : beanDefinition.getPropertyDefinitions()) {
					String property = propertyDefinition.getName();
					Method method = getBeanMethod(className,property);
					String ref = propertyDefinition.getRef();
					if (ref!=null&&!"".equals(ref)) {
						method.invoke(bean, getBean(ref));
					}else {
						setValue(method, bean, propertyDefinition.getValue());
					}
				}
				beans.put(id, bean);
				return bean;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private Method getBeanMethod(Class<?> className, String property) {
		StringBuffer sb = new StringBuffer("set");
		char firstChar = property.charAt(0);
		if (firstChar>='a'&&firstChar<='z') {
			firstChar -=32;
		}
		sb.append(firstChar).append(property.substring(1));
		Method[] methods = className.getMethods();
		for (Method m : methods) {
			String methodName = m.getName();
			if (methodName.equals(sb.toString())) {
				return m;
			}
		}
		return null;
	}

	private BeanDefinition getBeanDefinition(String id){
		for (BeanDefinition beanDefinition : beanDefinitions) {
			if (beanDefinition.getId().equals(id)) {
				return beanDefinition;
			}
		}
		return null;
	}
	
	private void setValue(Method m, Object bean, String value) throws Exception{
        // TODO Auto-generated method stub
        Class paramType = m.getParameterTypes()[0];
        if(paramType == byte.class || paramType == Byte.class){
               Byte b = new Byte(value);
               m.invoke(bean, b);
        }else if(paramType == short.class || paramType == Short.class){
               Short s = new Short(value);
               m.invoke(bean, s);
        }else if(paramType == char.class || paramType == Character.class){
               Character c = new Character(value.charAt(0));
               m.invoke(bean, c);
        }else if(paramType == int.class || paramType == Integer.class){
               Integer i = new Integer(value);
               m.invoke(bean, i);
        }else if(paramType == float.class || paramType == Float.class){
               Float f = new Float(value);
               m.invoke(bean, f);
        }else if(paramType == double.class || paramType == Double.class){
               Double d = new Double(value);
               m.invoke(bean, d);
        }else{
               m.invoke(bean, value);
        }
 }

}
