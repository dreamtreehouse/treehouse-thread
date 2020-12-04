package com.treehouse.thread.patterns;

import java.util.LinkedList;

import com.treehouse.thread.util.PrintUtil;

/**
 * 生产者消费者模式
 */
public class ProducerConsumerPattern {
	
	public static void main(String[] args){
		
		//初始化消息队列
		MessageQueue queue = new MessageQueue(2);
		
		// 生产者线程
		for(int i = 0; i < 3; i++){
			int id = i;
			new Thread(() -> {
				queue.put(new Message(id,id));
			} , "Producer[" + (i + 1) + "]").start();
		}
		
		// 消费者线程
		new Thread(() -> {
			try {
				while(true){
					Thread.sleep(1000);
					queue.take();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		},"Consumer").start();
	}
}

//消息队列,用于线程间通信
class MessageQueue{
	
	//消息的队列集合
	private LinkedList<Message> list = new LinkedList<Message>();
	//队列容量
	private int capcity;
	
	public MessageQueue(int capcity){
		this.capcity = capcity;
	}
	
	//消费消息
	public Message take(){
		synchronized (list) {
			while(list.isEmpty()){
				try {
					PrintUtil.log("队列为空，消费者线程等待...");
					list.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			//消费消息
			Message message = list.removeFirst();
			PrintUtil.log("消费了一条消息 " + message.toString());
			//唤醒生产者
			list.notifyAll();
			
			return message;
		}
	}
	
	//生产消息
	public void put(Message message){
		synchronized (list) {
			while(list.size() == capcity){
				try {
					PrintUtil.log("队列已满，生产者线程等待...");
					list.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			//生产消息
			list.addLast(message);
			PrintUtil.log("生产了一条消息 " + message.toString());
			//唤醒消费者
			list.notifyAll();
		}
	}
}

//消息对象
class Message{
	
	private int id;
	private Object value;
	
	public Message(int id, Object value){
		this.id = id;
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public Object getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", value=" + value + "]";
	}
}