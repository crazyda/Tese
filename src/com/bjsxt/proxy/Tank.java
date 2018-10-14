package com.bjsxt.proxy;
import java.util.Random;


public class Tank implements Moveable {

	@Override
	public void move() {
		long start = System.currentTimeMillis();
		System.out.println("Tank moving.....");
		
		try {
			//睡眠一段随机时间
			Thread.sleep(new Random().nextInt(10000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
