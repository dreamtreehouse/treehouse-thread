package com.treehouse.thread.patterns;

import com.treehouse.thread.util.PrintUtil;

/**
 * 固定次序运行模式：wait|notify实现
 */
public class WaitNotifySequentialPattern {
	
	static final Object lock = new Object();
	
	static boolean isFirstRunned = false;
	
	public static void main(String[] args){
		
		Thread second = new Thread(() -> {
			synchronized (lock) {
				if(!isFirstRunned){
					try {
						PrintUtil.log("I am waiting for first !");
						lock.wait();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				PrintUtil.log("I am running second !");
			}
		}, "Second");
		
		Thread first = new Thread(() -> {
			synchronized (lock) {
				PrintUtil.log("I am running first !");
				isFirstRunned = true;
				lock.notify();
			}
		}, "First");
		
		second.start();
		first.start();
	}
}
