package com.sample.microservices.kafka.data.model;

import java.time.LocalDate;

import com.sample.microservices.common.model.ParentEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="kafka_consumer_message_3")

@Getter
@Setter
public class KafkaConsumerMessageEntity extends ParentEntity {
	
	private static final long serialVersionUID = 8436014022103292703L;
	
	@Column(name="topic_num")
	private String topic;
	
	@Column(name="group_num")
	private String group;
	
	@Column(name="message")
	private String message;
	
	@Column(name="partition")
	private String partition;
	
	@Column(name="offset_num")
	private Integer offset;
	
	@Column(name="created_date")
	private LocalDate createdDate;
	
}
