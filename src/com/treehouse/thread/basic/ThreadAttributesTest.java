package com.treehouse.thread.basic;

import com.treehouse.thread.util.PrintUtil;

public class ThreadAttributesTest {
	public static void main(String[] args){
		
		Thread thread = Thread.currentThread();
		
		PrintUtil.log("线程ID：[" + thread.getId() +"]");
		PrintUtil.log("线程名称：[" + thread.getName() +"]");
		PrintUtil.log("线程状态：[" + thread.getState() +"]");
		PrintUtil.log("线程优先级：[" + thread.getPriority() +"]");
		PrintUtil.log("线程类别：[" + (thread.isDaemon() ? "守护线程" : "用户线程") +"]");
		
	}
}
