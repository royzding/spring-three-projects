package com.sample.microservices.rabbitmq.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Data
public class Payload {
	
	@JsonProperty("MSG_1")
    private String message1;
    private String message2;
    private Context context;
    private Integer taskId;
	@Override
	public String toString() {
		return "Payload [message1=" + message1 + ", message2=" + message2 + ", context=" + context + ", taskId="
				+ taskId + "]";
	}   
}
