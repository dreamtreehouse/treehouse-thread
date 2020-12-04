package com.treehouse.thread.basic;

import java.util.concurrent.Callable;

/**
 * 创建线程的三种方式
 * 	1.继承Thread类，重写run()方法.
 * 	2.实现Runnable接口，实现run()方法.
 * 	3.实现Callable接口，实现call()方法.
 */
public class ThreadCreateTest {
	
	public static void main(String[] args){
		
		Thread t1 = new Thread(){
			@Override
			public void run() {
				System.out.println("Running");
			}
		};
		
		t1.start();
		
	}
}


class MyThread extends Thread{

	@Override
	public void run() {
		System.out.println("I am a thread extends Thread.");
	}
}

class MyRunnable implements Runnable{

	@Override
	public void run() {
		System.out.println("I am a thread implements Runnble.");
	}
}

class MyCallable<V> implements Callable<V>{

	@Override
	public V call() throws Exception {
		System.out.println("I am a thread implements Callable.");
		return null;
	}
}