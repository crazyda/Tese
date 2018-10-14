package com.bjsxt.proxy;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class Client {

	public static void main(String[] args) throws Exception {
		Tank t = new Tank();
		//这里在这里变换,实现先记录时间后记录log 或者先记录log在记录时间
		
//		TankTimeProxy ttp = new TankTimeProxy(t);
//		TankLogProxy tlp = new TankLogProxy(ttp);
//		
//		Moveable m = tlp;
//		m.move();
//		//客户端知道要调用那个接口, 并做怎么样的处理
		//动态代理, 对任意的对象,任意接口方法,实现任意的动态代理
		Moveable m = (Moveable) Proxy.newProxyInstance(Moveable.class,new TImeHandler(t));
		
		m.move();
		
	}

}
