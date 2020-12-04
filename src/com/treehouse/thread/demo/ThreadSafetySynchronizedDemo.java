package com.treehouse.thread.demo;

/**
 * 案例：
 * 		两个线程对同一个静态变量进行自增和自减相同次数的操作，最后结果不为0.
 * 方案：
 * 		synchronized加锁
 * 
 */
public class ThreadSafetySynchronizedDemo {
	
	public static int counter = 0;
	
	public static final Object lock = new Object();
	
	public static void main(String[] args) throws InterruptedException{
		
		//线程1对counter进行5000次+1操作
		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 5000; i++) {
				synchronized (lock) {
					counter++;
				}
			}
		});
		
		//线程2对counter进行5000次-1操作
		Thread t2 = new Thread(() -> {
			for (int i = 0; i < 5000; i++) {
				synchronized (lock) {
					counter--;
				}
			}
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		System.out.println("counter=" + counter);
		
	}
}
