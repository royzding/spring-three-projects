package com.sample.microservices.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.sample.microservices.kafka.data.model.KafkaConsumerMessageEntity;
import com.sample.microservices.kafka.service.KafKaConsumerService;
 
@Service
public class KafkaTopic04Consumer
{
    private final Logger logger = LoggerFactory.getLogger(KafkaTopic04Consumer.class);
    
    private final KafKaConsumerService kcService;
    
    KafkaTopic04Consumer(final KafKaConsumerService kcService) {
    	this.kcService = kcService;    	
    }

    @Value("${spring.kafka.topic01-name}")
    private String topic01Name;
 
    @Value("${spring.kafka.topic02-name}")
    private String topic02Name;
 
    @Value("${spring.kafka.topic03-name}")
    private String topic03Name;
 
    @Value("${spring.kafka.topic04-name}")
    private String topic04Name;
    
    @Value("${spring.kafka.consumer.group00-id}")
    private String group00Id;
    
    @Value("${spring.kafka.consumer.group01-id}")
    private String group01Id;
    
    @Value("${spring.kafka.consumer.group02-id}")
    private String group02Id;
    
    @Value("${spring.kafka.consumer.group03-id}")
    private String group03Id;
    
    @Value("${spring.kafka.consumer.group04-id}")
    private String group04Id;
    
   //listening on TOPIC04 in GROUP01
    
    @KafkaListener(topics = "${spring.kafka.topic04-name}")
    public void listenOnTopic01WithHeaders(@Payload String message, 
    		@Header(KafkaHeaders.OFFSET) Integer offset,
            @Header(KafkaHeaders.RECEIVED_PARTITION) String partition,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) 
    {    	
    	this.kcService.save("listenOnTopic01", topic, this.group01Id, message, partition, offset);    	
     	logger.info("Processing topic = {}, partition = {}, offset = {}, message = {}",
                topic, partition, offset, message);         
    }

    @KafkaListener(topics = "${spring.kafka.topic04-name}", groupId = "${spring.kafka.consumer.group01-id}")
    public void T4G1_consumer1(String message) 
    {
    	this.kcService.save("T4G1_consumer1", this.topic04Name, this.group01Id, message);    	
    	logger.info("T4G1_consumer1: Message recieved:" + message);
    }

    //listening on TOPIC04 in GROUP02
    
    @KafkaListener(topics = "${spring.kafka.topic04-name}", groupId = "${spring.kafka.consumer.group02-id}")
    public void T4G2_consumer1(String message) 
    {
    	this.kcService.save("T4G2_consumer1", this.topic04Name, this.group02Id, message);    	
    	logger.info("T4G2_consumer1: Message recieved:" + message);
    }

    @KafkaListener(topics = "${spring.kafka.topic04-name}", groupId = "${spring.kafka.consumer.group02-id}")
    public void T4G2_consumer2(String message) 
    {
    	this.kcService.save("T4G2_consumer2", this.topic04Name, this.group02Id, message);    	
    	logger.info("T4G2_consumer2: Message recieved:" + message);
    }

    //listening on TOPIC04 in GROUP03

    @KafkaListener(topics = "${spring.kafka.topic04-name}", groupId = "${spring.kafka.consumer.group03-id}")
    public void T4G3_consumer1(String message) 
    {
    	this.kcService.save("T4G3_consumer1", this.topic04Name, this.group03Id, message);    	
    	logger.info("T4G3_consumer1: Message recieved:" + message);
    }

    @KafkaListener(topics = "${spring.kafka.topic04-name}", groupId = "${spring.kafka.consumer.group03-id}")
    public void T4G3_consumer2(String message) 
    {
    	this.kcService.save("T4G3_consumer2", this.topic04Name, this.group03Id, message);    	
    	logger.info("T4G3_consumer2: Message recieved:" + message);
    }

    @KafkaListener(topics = "${spring.kafka.topic04-name}", groupId = "${spring.kafka.consumer.group03-id}")
    public void T4G3_consumer3(String message) 
    {
    	this.kcService.save("T4G3_consumer3", this.topic04Name, this.group03Id, message);    	
    	logger.info("T4G3_consumer3: Message recieved:" + message);
    }


    //listening on TOPIC04 in GROUP04

    @KafkaListener(topics = "${spring.kafka.topic04-name}", groupId = "${spring.kafka.consumer.group04-id}")
    public void T4G4_consumer1(String message) 
    {
    	this.kcService.save("T4G4_consumer1", this.topic04Name, this.group04Id, message);    	
    	logger.info("T4G4_consumer1: Message recieved:" + message);
    }

    @KafkaListener(topics = "${spring.kafka.topic04-name}", groupId = "${spring.kafka.consumer.group04-id}")
    public void T4G4_consumer2(String message) 
    {
    	this.kcService.save("T4G4_consumer2", this.topic04Name, this.group04Id, message);    	
    	logger.info("T4G4_consumer2: Message recieved:" + message);
    }

    @KafkaListener(topics = "${spring.kafka.topic04-name}", groupId = "${spring.kafka.consumer.group04-id}")
    public void T4G4_consumer3(String message) 
    {
    	this.kcService.save("T4G4_consumer3", this.topic04Name, this.group04Id, message);    	
    	logger.info("T4G4_consumer3: Message recieved:" + message);
    }

    @KafkaListener(topics = "${spring.kafka.topic04-name}", groupId = "${spring.kafka.consumer.group04-id}")
    public void T4G4_consumer4(String message) 
    {
    	this.kcService.save("T4G4_consumer4", this.topic04Name, this.group04Id, message);    	
    	logger.info("T4G4_consumer4: Message recieved:" + message);
    }


    
}