package com.lyj.base.conn008;

import java.util.ArrayList;
import java.util.List;

public class ListAdd1 {

	
	private volatile static List list = new ArrayList();	
	
	public void add(){
		list.add("haha");
	}
	public int size(){
		return list.size();
	}
	
	public static void main(String[] args) {
		
		final ListAdd1 list1 = new ListAdd1();
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					for(int i = 0; i <10; i++){
						list1.add();
						System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素..");
						Thread.sleep(500);
					}	
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t1");
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				//一直轮询去判断，改善的方式是使用线程间的通信。例如： list1.size() == 5 了，则  t1  notify t2 
				while(true){
					if(list1.size() == 5){
						System.out.println("当前线程收到通知：" + Thread.currentThread().getName() + " list size = 5 线程停止..");
						throw new RuntimeException();
					}
				}
			}
		}, "t2");		

		t1.start();
		t2.start();
	}
	
}
