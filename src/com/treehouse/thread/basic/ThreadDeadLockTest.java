package com.treehouse.thread.basic;

import com.treehouse.thread.util.PrintUtil;
import com.treehouse.thread.util.ThreadUtil;

/**
 *	线程死锁：
 *		线程一占有A锁的同时申请B锁，线程二占有B锁的同时申请A锁，互相等待对方持有的锁形成死锁。
 */
public class ThreadDeadLockTest {
	
	public static void main(String[] args){
		
		Object lockA = new Object();
		Object lockB = new Object();
		
		new Thread(() -> {
			synchronized (lockA) {
				PrintUtil.log("lock A");
				ThreadUtil.sleep(1000);
				synchronized (lockB) {
					PrintUtil.log("lock B");
					PrintUtil.log("执行...");
				}
			}
		}, "T1").start();
		
		
		new Thread(() -> {
			synchronized (lockB) {
				PrintUtil.log("lock B");
				ThreadUtil.sleep(1000);
				synchronized (lockA) {
					PrintUtil.log("lock A");
					PrintUtil.log("执行...");
				}
			}
		}, "T2").start();
		
	}
}
