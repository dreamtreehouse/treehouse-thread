package com.treehouse.thread.basic;

public class ThreadJoinTest {
	
	public static void main(String[] args){
		
		Thread t = new Thread(() -> {});
		
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
