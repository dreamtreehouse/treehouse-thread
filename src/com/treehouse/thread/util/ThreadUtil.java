package com.treehouse.thread.util;

/**
 * @ClassName: ThreadUtil 
 * @Description: 线程工具类
 * @author HongDa
 * @date 2020年11月5日 下午2:52:28
 */
public class ThreadUtil {
	
	public static void sleep(long millis){
		
		if(millis <= 0)
			return;
		
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
