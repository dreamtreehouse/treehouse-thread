package com.treehouse.thread.patterns;

import com.treehouse.thread.util.PrintUtil;

/**
 * 线程交替执行模式：wait|notify实现
 * 		线程T1、T2、T3 交替输出a、b、c五次，结果为：abcabcabcabcabc
 */
public class WaitNotifyAlternatePattern {
	
	public static final Object lock = new Object();
	public static int flag = 1;	//线程执行标识:1=T1执行,2=T2执行,3=T3执行
	
	public static void main(String[] args){
		
		Thread t1 = new Thread(() -> {
			int count = 5;
			while(count > 0){
				synchronized (lock) {
					while(flag != 1){
						try {
							lock.wait();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					PrintUtil.log("a");
					count--;
					flag = 2;
					lock.notifyAll();
				}
			}
		}, "T1");
		
		
		Thread t2 = new Thread(() -> {
			int count = 5;
			while(count > 0){
				synchronized (lock) {
					while(flag != 2){
						try {
							lock.wait();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					PrintUtil.log("b");
					count--;
					flag = 3;
					lock.notifyAll();
				}
			}
		}, "T2");
		
		
		Thread t3 = new Thread(() -> {
			int count = 5;
			while(count > 0){
				synchronized (lock) {
					while(flag != 3){
						try {
							lock.wait();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					PrintUtil.log("c");
					count--;
					flag = 1;
					lock.notifyAll();
				}
			}
		}, "T3");
		
		
		t3.start();
		t1.start();
		t2.start();
	}
}
