package com.treehouse.thread.demo;

import com.treehouse.thread.util.PrintUtil;

/**
 * 案例：
 * 		公司有7个员工，但只有1台电脑，只能轮流使用，其中大明工作时必须抽烟(需要买)，烟到了才能工作，小明需要等外卖到了才能工作。
 */
public class ThreadWaitNotifyDemo {
	
	public static final Object computer = new Object();
	public static boolean hasCigarette = false;//是否有香烟
	public static boolean hasDeliver = false;//是否有外卖
	
	public static void main(String[] args){
		
		//大明需要等香烟
		new Thread(() -> {
			synchronized(computer){
				PrintUtil.log("有烟没 ? " + hasCigarette);
				while(!hasCigarette){
					//没有香烟
					PrintUtil.log("没烟  , 先歇会 !");
					try {
						computer.wait();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				PrintUtil.log("有烟没 ? " + hasCigarette);
				if(hasCigarette){
					//有香烟
					PrintUtil.log("可以开始干活了");
				}else{
					PrintUtil.log("没干成活...");
				}
			}
		}, "大明").start();
		
		//小明需要等外卖
		new Thread(() -> {
			synchronized(computer){
				PrintUtil.log("外卖送到没 ? " + hasDeliver);
				if(!hasDeliver){
					//没有外卖
					PrintUtil.log("没外卖 , 先歇会 !");
					try {
						computer.wait();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				PrintUtil.log("外卖送到没 ? " + hasDeliver);
				if(hasDeliver){
					PrintUtil.log("可以开始干活了");
				}else{
					PrintUtil.log("没干成活...");
				}
			}
		}, "小明").start();
		
		//其他员工
		for(int i = 0; i < 5; i++){
			new Thread(() -> {
				synchronized (computer) {
					PrintUtil.log("可以开始干活了");
				}
			},"员工" + (i + 1)).start();
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		new Thread(() ->{
			synchronized (computer) {
				hasDeliver = true;
				PrintUtil.log("外卖送到了噢!");
				computer.notifyAll();
			}
		},"送外卖的").start();;
	}
}
