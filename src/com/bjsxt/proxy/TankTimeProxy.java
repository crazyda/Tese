package com.bjsxt.proxy; 
import java.lang.reflect.Method;
public class TankTimeProxy implements com.bjsxt.proxy.Moveable{ 
public TankTimeProxy(InvocationHandler h){ 
super(); 
this.h = h; 
} 
//Tank t; 
com.bjsxt.proxy.InvocationHandler h; 
@Override 
public void move() { 
    try{
    Method md = com.bjsxt.proxy.Moveable.class.getMethod("move");
    h.invoke(this,md);
    }catch(Exception e) { e.printStackTrace();}
}
;}