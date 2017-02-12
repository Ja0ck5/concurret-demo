package com.lyj.height.masterworker;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		final int WORKERCOUNT = 10;
		Master master = new Master(new MyWorker(),WORKERCOUNT);
		
		
		
		//mock submit task
		for (int i = 0; i < 100; i++) {
			Task task = new Task();
			task.setId(i);
			task.setName("task-"+i);
			task.setPrice(new Random().nextInt(1000));
			//submit task
			master.submit(task);
		}
		
		//execute
		master.execute();
		long start = System.currentTimeMillis();
		//判断是否执行完毕
		while(true){
			long time = System.currentTimeMillis() - start;
			if(master.isComplete()){
				System.out.println("time waste:"+ time +"\r\nresult:"+ master.getResult());
				break;
			}
		}
		
	}
}
