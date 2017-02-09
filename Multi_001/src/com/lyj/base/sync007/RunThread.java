package com.lyj.base.sync007;

public class RunThread extends Thread{

	/**
	 * volatile 保证可见性
	 */
	private volatile boolean isRunning = true;
	private void setRunning(boolean isRunning){
		this.isRunning = isRunning;
	}
	
	public void run(){
		System.out.println("进入run方法..");
		int i = 0;
		/*加了 volatile 修饰，修改了变量之后会写会主存，从而可见 此时 isRunning 为 false. 
		 * 如果没有 volatile 修饰，那么是不可见的，这里仍然为 true 一直空轮询
		 * */
		while(isRunning == true){
			//..
		}
		System.out.println("线程停止");
	}
	
	public static void main(String[] args) throws InterruptedException {
		RunThread rt = new RunThread();
		rt.start();
		Thread.sleep(1000);
		rt.setRunning(false);
		System.out.println("isRunning的值已经被设置了false");
	}
	
}
