package com.treehouse.thread.basic;

import java.util.concurrent.locks.ReentrantLock;

import com.treehouse.thread.util.PrintUtil;
import com.treehouse.thread.util.ThreadUtil;

/**
 * ReentrantLock：可中断测试
 */
public class ReentrantLockInterruptTest {
	
	private static ReentrantLock lock = new ReentrantLock();
	
	public static void main(String[] args){
		
		Thread t = new Thread(() -> {
			
			try {
				PrintUtil.log("尝试获取锁");
				lock.lockInterruptibly();
			} catch (InterruptedException e) {
				PrintUtil.log("没有获取到锁(被打断)");
				e.printStackTrace();
				return;
			}
			
			try {
				PrintUtil.log("获取到锁");
			} finally {
				lock.unlock();
			}
		}, "T");
		
		
		lock.lock();
		t.start();
		
		ThreadUtil.sleep(1000);
		
		PrintUtil.log("打断T线程");
		t.interrupt();
//		try {
//			
//		} finally {
//			lock.unlock();
//		}
	}
}
