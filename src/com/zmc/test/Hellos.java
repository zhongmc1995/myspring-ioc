package com.zmc.test;

public class Hellos {
	private Hello hello;

	public Hello getHello() {
		return hello;
	}

	public void setHello(Hello hello) {
		this.hello = hello;
	}
	
	public void sayHello(){
		hello.say();
	}
}
