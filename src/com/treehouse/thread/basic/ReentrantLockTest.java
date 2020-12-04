package com.treehouse.thread.basic;

import java.util.concurrent.locks.ReentrantLock;

import com.treehouse.thread.util.PrintUtil;

/**
 * ReentrantLock：可重入锁
 */
public class ReentrantLockTest {
	
	private static ReentrantLock lock = new ReentrantLock();
	
	public static void main(String[] args){
		
		lock.lock();
		
		try {
			PrintUtil.log("Enter main");
			methodA();
		} finally {
			lock.unlock();
		}
		
	}
	
	public static void methodA(){
		
		lock.lock();
		
		try {
			PrintUtil.log("Enter method A");
			methodB();
		} finally {
			lock.unlock();
		}
	}
	
	public static void methodB(){
		
		lock.lock();
		
		try {
			PrintUtil.log("Enter method B");
		} finally {
			lock.unlock();
		}
	}
}
