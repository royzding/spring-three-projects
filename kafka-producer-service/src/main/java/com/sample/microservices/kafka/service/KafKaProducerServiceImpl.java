package com.sample.microservices.kafka.service;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.sample.microservices.kafka.model.User;

@Service
public class KafKaProducerServiceImpl implements KafKaProducerService {

    private static final Logger logger = LoggerFactory.getLogger(KafKaProducerServiceImpl.class);
     
    @Value("${spring.kafka.topic01-name}")
    private String topic01Name;
 
    @Value("${spring.kafka.topic02-name}")
    private String topic02Name;
 
    @Value("${spring.kafka.topic03-name}")
    private String topic03Name;
 
    @Value("${spring.kafka.topic04-name}")
    private String topic04Name;
 
    public static final String TOPIC_NAME = "quickstart-events";

    private final KafkaTemplate<String, String> kafkaTemplate;
    
    private final KafkaTemplate<String, User> objectKafkaTemplate;
    
    KafKaProducerServiceImpl( final KafkaTemplate<String, String> kafkaTemplate,
    		final KafkaTemplate<String, User> objectKafkaTemplate) {
    	this.kafkaTemplate = kafkaTemplate;
    	this.objectKafkaTemplate = objectKafkaTemplate;
    }

    public String sendMessageToTopic01(String message) 
    {
    	CompletableFuture<SendResult<String, String>> future = this.kafkaTemplate.send(topic01Name, message);
    	
    	loggingMessage(future, message);

        return topic01Name;
    }
    
    public String sendMessageToTopic02(String message) 
    {
    	CompletableFuture<SendResult<String, String>> future = this.kafkaTemplate.send(topic02Name, message);
        		
    	loggingMessage(future, message);

    	return topic02Name;
    	
    }
    
    public String sendMessageToTopic03(String message) 
    {
    	CompletableFuture<SendResult<String, String>> future = this.kafkaTemplate.send(topic03Name, message);
        		
    	loggingMessage(future, message);
    	
        return topic03Name;
    	
    }
    
    public String sendMessageToTopic04(String message) 
    {
    	CompletableFuture<SendResult<String, String>> future = this.kafkaTemplate.send(topic04Name, message);
        		
    	loggingMessage(future, message);
    	
        return topic04Name;
    	
    }
    
    public String sendMessageToTopic04WithPartitionId2(Integer pid2, String message) 
    {
        Message<String> msg = MessageBuilder
        .withPayload(message)
        .setHeader(KafkaHeaders.TOPIC, topic04Name)
        .setHeader(KafkaHeaders.PARTITION, pid2)
        .build();
        
        CompletableFuture<SendResult<String, String>> future = this.kafkaTemplate.send(msg);
        		
    	loggingMessage(future, message);
    	
        return topic04Name + "pid2";
    	
    }
    
    public String sendMessageToTopic03(User user) 
    {
    	CompletableFuture<SendResult<String, User>> future = this.objectKafkaTemplate.send(topic03Name, user);
        		
    	loggingUser(future, user);
    	
        return topic03Name;
    }
    
    public String sendMessageToTopic04(User user) 
    {
    	CompletableFuture<SendResult<String, User>> future = this.objectKafkaTemplate.send(topic04Name, user);
        		
    	loggingUser(future, user);
    	
        return topic04Name;
    }
    
    private void loggingMessage(CompletableFuture<SendResult<String, String>> future, String message) {
    	
        future.whenComplete((result, ex) -> {
        	
            if (ex == null) {
            	logger.info("Sent message=[" + message  + 
    					"] with offset=[" + result.getRecordMetadata().offset() + 
    					"] with partition=[" + result.getRecordMetadata().partition() + 
        				"] to Topic=[" + result.getProducerRecord().topic());
            } else {
            	logger.info("Unable to send message=[" + message + "] due to : " + ex.getMessage());
            }
        });    	
    	
    	
    }
    
    private void loggingUser(CompletableFuture<SendResult<String, User>> future, User user) {
    	
        future.whenComplete((result, ex) -> {
        	
            if (ex == null) {
            	logger.info("Sent message=[" + user  + 
    					"] with offset=[" + result.getRecordMetadata().offset() + 
    					"] with partition=[" + result.getRecordMetadata().partition() + 
        				"] to Topic=[" + result.getProducerRecord().topic());
            } else {
            	logger.info("Unable to send message=[" + user + "] due to : " + ex.getMessage());
            }
        });    	
    	
    	
    }
    
}