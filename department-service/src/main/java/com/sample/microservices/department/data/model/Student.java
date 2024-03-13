package com.sample.microservices.department.data.model;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

import lombok.Data;

@Data
@RedisHash("Student")
public class Student implements Serializable {
  
    private static final long serialVersionUID = 1L;
    
	public enum Gender { 
        MALE, FEMALE
    }

    private Long id;
    private String name;
    private Gender gender;
    private int grade;

}
