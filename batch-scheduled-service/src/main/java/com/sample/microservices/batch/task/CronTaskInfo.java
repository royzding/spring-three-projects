package com.sample.microservices.batch.task;

import lombok.Data;

@Data
public class CronTaskInfo {

	private Integer id;
	private String name;
	private String cronStr;
    private String message;

}