package com.treehouse.thread.basic;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import com.treehouse.thread.util.PrintUtil;

/**
 * ReentrantLock：条件变量测试
 */
public class ReentrantLockConditionTest {

	public static final ReentrantLock computer = new ReentrantLock();
	
	public static boolean hasCigarette = false;//是否有香烟
	public static Condition waitCigaretteSet = computer.newCondition();
	
	public static boolean hasDeliver = false;//是否有外卖
	public static Condition waitDeliverSet = computer.newCondition();
	
	public static void main(String[] args){
		
		//大明需要等香烟
		new Thread(() -> {
			
			computer.lock();
			
			try {
				
				PrintUtil.log("有烟没 ? " + hasCigarette);
				while(!hasCigarette){
					//没有香烟
					PrintUtil.log("没烟  , 先歇会 !");
					try {
						waitCigaretteSet.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				PrintUtil.log("可以开始干活了");
			} finally {
				computer.unlock();
			}
		}, "大明").start();
		
		//小明需要等外卖
		new Thread(() -> {
			
			computer.lock();
			
			try {
				
				PrintUtil.log("外卖送到没 ? " + hasDeliver);
				if(!hasDeliver){
					//没有外卖
					PrintUtil.log("没外卖 , 先歇会 !");
					try {
						waitDeliverSet.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				PrintUtil.log("可以开始干活了");
			} finally {
				computer.unlock();
			}
		}, "小明").start();
		
		//其他员工
		for(int i = 0; i < 5; i++){
			new Thread(() -> {
				computer.lock();
				try {
					PrintUtil.log("可以开始干活了");
				} finally {
					computer.unlock();
				}
			},"员工" + (i + 1)).start();
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		new Thread(() ->{
			computer.lock();
			try {
				hasDeliver = true;
				PrintUtil.log("外卖送到了噢!");
				waitDeliverSet.signal();
			} finally {
				computer.unlock();
			}
		},"送外卖的").start();;
	}
}
