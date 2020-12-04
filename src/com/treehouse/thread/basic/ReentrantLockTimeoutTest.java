package com.treehouse.thread.basic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import com.treehouse.thread.util.PrintUtil;
import com.treehouse.thread.util.ThreadUtil;

/**
 * ReentrantLock：锁超时测试
 */
public class ReentrantLockTimeoutTest {
	
	private static ReentrantLock lock = new ReentrantLock();
	public static void main(String[] args){
		
		Thread t = new Thread(() -> {
			
			PrintUtil.log("尝试获取锁");
			try {
				if(!lock.tryLock(2, TimeUnit.SECONDS)){
					PrintUtil.log("获取不到锁");
					return;
				}
			} catch (InterruptedException e) {
				PrintUtil.log("获取不到锁(被打断)");
				e.printStackTrace();
				return;
			};
			
			try {
				PrintUtil.log("获取到锁");
			} finally {
				lock.unlock();
			}
		}, "T");
		
		lock.lock();
		PrintUtil.log("获得到锁");
		
		t.start();
		
		ThreadUtil.sleep(1000);
		PrintUtil.log("释放锁");
		lock.unlock();
	}
}
