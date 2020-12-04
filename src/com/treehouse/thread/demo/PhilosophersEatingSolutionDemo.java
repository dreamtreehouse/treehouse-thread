package com.treehouse.thread.demo;

import java.util.concurrent.locks.ReentrantLock;

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
 * 方案：
 * 		ReentrantLock锁超时,若哲学家等不到右筷子就释放掉左筷子.
 */
public class PhilosophersEatingSolutionDemo {

	public static void main(String[] args){
		
		Chopsticks c1 = new Chopsticks("c1");
		Chopsticks c2 = new Chopsticks("c2");
		Chopsticks c3 = new Chopsticks("c3");
		Chopsticks c4 = new Chopsticks("c4");
		Chopsticks c5 = new Chopsticks("c5");
		
		new Philosophers("苏格拉底", c1, c2).start();
		new Philosophers("柏拉图", c2, c3).start();
		new Philosophers("亚里士多德", c3, c4).start();
		new Philosophers("赫拉克利特", c4, c5).start();
		new Philosophers("阿基米德", c5, c2).start();
		
	}
}

//哲学家(线程)
class Philosophers extends Thread {
	
	private Chopsticks left;
	
	private Chopsticks right;
	
	public Philosophers(String name, Chopsticks left, Chopsticks right) {
		super(name);
		this.left = left;
		this.right = right;
	}

	@Override
	public void run() {
		while(true){
			//尝试拿起左筷子
			if(left.tryLock()){
				try {
					//尝试拿起右筷子
					if(right.tryLock()){
						try {
							PrintUtil.log("eating...");
							ThreadUtil.sleep(1000);
						} finally {
							//释放右筷子
							right.unlock();
						}
					}
				} finally {
					//释放左筷子
					left.unlock();
				}
			}
		}
	}
}

//筷子
class Chopsticks extends ReentrantLock {

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	public Chopsticks(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Chopstick [name=" + name + "]";
	}
}
