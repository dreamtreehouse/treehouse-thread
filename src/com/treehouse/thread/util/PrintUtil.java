package com.treehouse.thread.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PrintUtil {
	
	public static void log(String info){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		
		StringBuilder sb = new StringBuilder();
		sb.append(sdf.format(new Date()));
		sb.append(" [");
		sb.append(Thread.currentThread().getName());
		sb.append("] ");
		sb.append(" - ");
		sb.append(info);
		
		System.out.println(sb.toString());
	}
}
