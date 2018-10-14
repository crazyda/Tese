package com.bjsxt.proxy;

import java.lang.reflect.Method;

//方法调用代理器
public interface InvocationHandler {
	public void invoke(Object o,Method m);
}
