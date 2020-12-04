package com.treehouse.thread.basic;

import com.treehouse.thread.util.PrintUtil;

public class ThreadWaitNotify {
	
	public static final Object lock = new Object();
	
	public static void main(String[] args){
		
		new Thread(() -> {
			synchronized (lock) {
				
				PrintUtil.log("开始执行...");
				
				try {
					PrintUtil.log("歇一会...");
					lock.wait();
				} catch (Exception e) {
					e.printStackTrace();
				} 
				
				PrintUtil.log("继续执行...");
				
			}
		}, "t1");
		
		
		new Thread(() -> {
			synchronized (lock) {
				
				PrintUtil.log("开始执行...");
				try {
					PrintUtil.log("歇一会...");
					lock.wait();
				} catch (Exception e) {
					e.printStackTrace();
				}
				PrintUtil.log("继续执行...");
			}
		}, "t1").start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		synchronized (lock) {
			PrintUtil.log("唤醒其他人...");
			lock.notify();
		}
	}
}
