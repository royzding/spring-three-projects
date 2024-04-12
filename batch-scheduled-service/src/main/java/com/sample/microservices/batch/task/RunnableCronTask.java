package com.sample.microservices.batch.task;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RunnableCronTask implements Runnable {

	private CronTaskInfo cronTask;
 
    public RunnableCronTask(CronTaskInfo cronTask) {
    	this.cronTask = cronTask;
    }

    @Override
    public void run() {
        System.out.println("@Time: " + LocalDateTime.now() + "Runnable Task with " + cronTask.getId() 
        + " on thread " + cronTask.getName() + ":" + cronTask.getCronStr() + ":" + cronTask.getMessage() );
    }

}