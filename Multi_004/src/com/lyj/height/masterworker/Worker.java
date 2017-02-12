package com.lyj.height.masterworker;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Worker implements Runnable{

	ConcurrentLinkedQueue<Task> workerQueue;
	
	ConcurrentHashMap<String, Object> resultMap;

	public void setWorkerQueue(ConcurrentLinkedQueue<Task> workerQueue) {
		this.workerQueue = workerQueue;
	}

	public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	
	@Override
	public void run() {
		while(true){
			//取出并移除
			Task input = this.workerQueue.poll();
			if (null == input) break;
			//实际处理业务逻辑
			this.resultMap.put(Integer.toString(input.getId()), handle(input));
		}
	}

	//处理业务
	public Object handle(Task input) {
/*		Object output = null;
		try {
			// mock service
			Thread.sleep(500);
			output = input.getPrice();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return output;*/
		return null;
	}

}
