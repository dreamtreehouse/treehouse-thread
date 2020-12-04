package com.treehouse.thread.demo;

/**
 * 案例：
 * 		两个线程对同一个静态变量进行自增和自减相同次数的操作，最后的结果不为0.
 * 原因：
 * 		自增自减操作并不是原子操作，线程上下文切换引起指令交错，导致线程安全问题.
 * 分析：
 * 		自增操作字节码指令:
 * 			getstatic
 * 			iconst_1
 * 			iadd
 * 			putstatic
 * 		自减操作字节码指令:
 * 			getstatic
 * 			iconst_1
 * 			isub
 * 			putstatic
 */
public class ThreadSafetyDemo {
	
	public static int counter = 0;
	
	public static void main(String[] args) throws InterruptedException {
		
		//线程1对counter进行5000次+1操作
		Thread t1 = new Thread(() -> {
			for(int i = 0; i < 5000; i++){
				counter++;
			}
		});
		
		//线程2对counter进行5000次-1操作
		Thread t2 = new Thread(() -> {
			for(int i = 0; i < 5000; i++){
				counter--;
			}
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		System.out.println("counter=" + counter);
	}
}
