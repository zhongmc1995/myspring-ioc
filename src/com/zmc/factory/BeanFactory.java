package com.zmc.factory;

/**
 * @author zhongmc
 * bean工厂接口
 */
public interface BeanFactory {
	//获取对应id的bean
	Object getBean(String id);
}
