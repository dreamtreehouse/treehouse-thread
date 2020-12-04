package com.treehouse.thread.demo;

import com.treehouse.thread.util.PrintUtil;
import com.treehouse.thread.util.ThreadUtil;

/**
 * 案例：	哲学家就餐问题
 * 		有五位哲学家，围坐在圆桌旁。
 * 		每位哲学家只做两件时间，思考和吃饭，思考一会儿吃口饭，吃晚饭接着思考。
 * 		吃饭时要用两根筷子吃，桌上共有5根筷子，每位哲学家左右手边各有一根筷子。
 * 		只要同时拥有两根筷子，才能吃饭，如果筷子被身边人拿着，自己就得等待。
 * 问题：
 * 		当五位哲学家都拿起左手边筷子时，互相等待右手筷子，形成死锁。
 */
public class PhilosophersEatingDemo {
	
	public static void main(String[] args){
		
		Chopstick c1 = new Chopstick("c1");
		Chopstick c2 = new Chopstick("c2");
		Chopstick c3 = new Chopstick("c3");
		Chopstick c4 = new Chopstick("c4");
		Chopstick c5 = new Chopstick("c5");
		
		new Philosopher("苏格拉底", c1, c2).start();
		new Philosopher("柏拉图", c2, c3).start();
		new Philosopher("亚里士多德", c3, c4).start();
		new Philosopher("赫拉克利特", c4, c5).start();
		new Philosopher("阿基米德", c5, c1).start();
		
	}
}

//哲学家类,类比线程
class Philosopher extends Thread{
	
	Chopstick left;	//左手边筷子
	Chopstick right;//右手边筷子
	
	public Philosopher(String name, Chopstick left, Chopstick right) {
		super(name);
		this.left = left;
		this.right = right;
	}

	@Override
	public void run() {
		while(true){
			//尝试获得左手边筷子
			synchronized (left) {
				//尝试获取右手边筷子
				synchronized (right) {
					PrintUtil.log("Eating...");
					ThreadUtil.sleep(1000);
				}
			}
		}
	}
}

//筷子类
class Chopstick{
	
	private String name;
	
	public Chopstick(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Chopstick [name=" + name + "]";
	}
}
