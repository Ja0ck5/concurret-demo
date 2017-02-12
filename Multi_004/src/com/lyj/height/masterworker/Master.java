package com.lyj.height.masterworker;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Master {

	//承装任务的集合
	private ConcurrentLinkedQueue<Task> workerQueue = new ConcurrentLinkedQueue<Task>();
	//worker 对象的容器
	private Map<String,Thread> workers = new HashMap<String,Thread>();
	//承装每个 Worker 的并发执行后的结果集
	private ConcurrentHashMap<String,Object> resultMap = new ConcurrentHashMap<String,Object>();
	
	//构造器
	public Master(Worker worker,int workerCount){
		//每一个worker 都需要拥有master的引用，workerQueue 获取任务， resultMap 提交结果
		//worker 需要拥有  承装任务的集合 的引用
		worker.setWorkerQueue(this.workerQueue);
		//worker 需要拥有  承装每个 Worker 的并发执行后的结果集 的引用
		worker.setResultMap(this.resultMap);
		
		for (int i = 0; i < workerCount; i++) {
			workers.put("worker-"+i, new Thread(worker));
		}
	}
	
	// 提交
	public void submit(Task task){
		this.workerQueue.add(task);
	}
	
	// 执行，启动应用程序，驱动所有 worker 工作
	public void execute(){
		for (Map.Entry<String, Thread> me : workers.entrySet()) {
			//启动线程
			me.getValue().start();
		}
	}

	//判断是否  execute  完毕
	public boolean isComplete() {
		for(Map.Entry<String, Thread> me : workers.entrySet()){
			if(me.getValue().getState() != Thread.State.TERMINATED)
				return false;
		}
		return true;
	}

	//返回结果集数据
	public long getResult() {
		long ret = 0L;
		for(Map.Entry<String, Object> me: resultMap.entrySet())
			// mock commplicated service
			ret += (Long)me.getValue();
		return ret;
	}
	
	
}
