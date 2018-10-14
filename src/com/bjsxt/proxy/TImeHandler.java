package com.bjsxt.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
//客户端决定要做的处理
public class TImeHandler implements InvocationHandler {
	
	private Object t;//被代理的对象
	
	


	public TImeHandler(Object t) {
		super();
		this.t = t;
	}




	@Override
	public void invoke( Object o,Method m) {
		long start = System.currentTimeMillis();
		try {
			//m.invoke(o,new Object[]{});
			m.invoke(t);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		long end = System.currentTimeMillis();
		System.out.println("time:"+(end-start));
	}

}
