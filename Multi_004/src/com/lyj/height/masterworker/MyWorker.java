package com.lyj.height.masterworker;

public class MyWorker extends Worker {
	// 处理业务
	public Object handle(Task input) {
		Object output = null;
		try {
			// mock service
			Thread.sleep(500);
			output = input.getPrice();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return output;
	}
}
