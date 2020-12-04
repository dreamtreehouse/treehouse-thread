package com.treehouse.thread.basic;

public class ThreadSleepTest {
	
	public static int count = 100;//计时
	
	public static void main(String[] args){
		
		while(count > 0){
			try {
				count--;
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
