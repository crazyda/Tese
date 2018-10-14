package com.bjsxt.proxy;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

public class Proxy {
	//infce 指定接口, h要对接口进行的处理
	public static Object newProxyInstance(Class infce,InvocationHandler h)throws Exception{
		//动态字符串 动态编译
		String rt = "\r\n";
		String methodStr = "";
		Method[] methods = infce.getMethods();
		for(Method m:methods){
			methodStr += "@Override" +" \r\n"+
			"public void "+m.getName()+"() {" +" \r\n"+
			"    try{"+ rt +
			"    Method md = " + infce.getName() +".class.getMethod(\""+m.getName()+"\");"+ rt +
			"    h.invoke(this,md);"+rt+
			"    }catch(Exception e) { e.printStackTrace();}"+rt+
			
			"}" +rt+";"
			;
			
		
		
		}
		
		
		
		
		String src = 
				"package com.bjsxt.proxy;" +" \r\n"+
"import java.lang.reflect.Method;"+rt+
"public class TankTimeProxy implements "+ infce.getName() +"{" +" \r\n"+
	"public TankTimeProxy(InvocationHandler h){" +" \r\n"+
		"super();" +" \r\n"+
		"this.h = h;" +" \r\n"+
	"}" +" \r\n"+
	
	"//Tank t;" +" \r\n"+
	"com.bjsxt.proxy.InvocationHandler h;" +" \r\n"+
	methodStr +
/*	"@Override" +" \r\n"+
	"public void move() {" +" \r\n"+
	"	long start = System.currentTimeMillis();" +" \r\n"+
	"	t.move();" +" \r\n"+
	"	long end = System.currentTimeMillis();" +" \r\n"+
	"	System.out.println(\"time:\"+(end-start));" +" \r\n"+
	"}" +" \r\n"+*/


"}";
		String fileName = System.getProperty("user.dir")+"/src/com/bjsxt/proxy/TankTimeProxy.java";
		File f = new File(fileName);
		FileWriter fw = new FileWriter(f);
		fw.write(src);
		fw.flush();
		fw.close();
		
		//拿到编译器对象
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		//管理动态生成的文件
		StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
		Iterable units = fileMgr.getJavaFileObjects(fileName);
		//
		CompilationTask ts = compiler.getTask(null, fileMgr, null, null, null, units);
		ts.call();
		fileMgr.close();
		
		//load到内存中, 并创建对象
		URL[] urls = new URL[]{new URL("file:/"+System.getProperty("user.dir")+"/src")};
		URLClassLoader ul = new URLClassLoader(urls);
		Class c = ul.loadClass("com.bjsxt.proxy.TankTimeProxy");
		System.out.println(c);
		//找一个构造方法,构造方法的参数类型是这个类型的
		Constructor ctr = c.getConstructor(InvocationHandler.class);
		Object m =  ctr.newInstance(h);
		
		
		
		
		
		
		return m;
		
	}
}
