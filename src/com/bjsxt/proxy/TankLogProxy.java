package com.bjsxt.proxy;
/*
 * 也是属于 代理 聚合方式
 */
public class TankLogProxy implements Moveable{
	public TankLogProxy(Moveable t){
		super();
		this.t = t;
	}
	
	
	Moveable t;
	
	@Override
	public void move() {
		System.out.println("Tank start....");
		t.move();
		System.out.println("Tank end....");
	}


}
