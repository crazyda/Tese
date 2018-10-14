package com.bjsxt.compiler.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

import com.bjsxt.proxy.Moveable;
import com.bjsxt.proxy.Tank;

public class Test1 {

	public static void main(String[] args) throws Exception{
		//生成编译文件 class
		String rt = "\r\n";
		String src = 
				"package com.bjsxt.proxy;" +" \r\n"+

"public class TankTimeProxy implements Moveable{" +" \r\n"+
	"public TankTimeProxy(Moveable t){" +" \r\n"+
		"super();" +" \r\n"+
		"this.t = t;" +" \r\n"+
	"}" +" \r\n"+
	
	"//Tank t;" +" \r\n"+
	"Moveable t;" +" \r\n"+
	
	"@Override" +" \r\n"+
	"public void move() {" +" \r\n"+
	"	long start = System.currentTimeMillis();" +" \r\n"+
	"	t.move();" +" \r\n"+
	"	long end = System.currentTimeMillis();" +" \r\n"+
	"	System.out.println(\"time:\"+(end-start));" +" \r\n"+
	"}" +" \r\n"+


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
		Constructor ctr = c.getConstructor(Moveable.class);
		Moveable m = (Moveable) ctr.newInstance(new Tank());
		m.move();
	}

}
