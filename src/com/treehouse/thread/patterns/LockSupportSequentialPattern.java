package com.treehouse.thread.patterns;

import java.util.concurrent.locks.LockSupport;

import com.treehouse.thread.util.PrintUtil;

/**
 * 固定次序运行模式：park|unpark实现
 */
public class LockSupportSequentialPattern {
	
	public static void main(String[] args){
		
		Thread second = new Thread(() -> {
			LockSupport.park();
			PrintUtil.log("I am running second !");
		},"Second");
		
		Thread first = new Thread(() -> {
			PrintUtil.log("I am running first !");
			LockSupport.unpark(second);
		},"First");
		
		first.start();
		second.start();
	}
}
