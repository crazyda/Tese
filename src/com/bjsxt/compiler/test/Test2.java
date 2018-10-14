package com.bjsxt.compiler.test;

import java.lang.reflect.Method;

public class Test2 {

	public static void main(String[] args) {
		//  动态生成接口中的方法 接口的方法,返回值,????
		Method[] methods = com.bjsxt.proxy.Moveable.class.getMethods();
		for(Method m:methods){
			System.out.println(m.getName());
		}

	}

}
