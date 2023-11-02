package com.sample.microservices.common.listener;

import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;

import com.sample.microservices.common.model.UserDetailStore;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;

public class EntityListener {
	
	@Autowired
	private UserDetailStore userDetailStore;
    
    @PrePersist
    @PreUpdate
    @PreRemove
    private void beforeAnyUpdate(Object target) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

    	String userName = userDetailStore.getUsername();
    	
    	System.out.println("[Entity AUDIT - beforeAnyUpdate: ] About to update/delete target: " 
    	+ target.getClass().getSuperclass().getCanonicalName());
    	
    	Field field = target.getClass().getSuperclass().getDeclaredField("modifiedBy");
    	   	    	
    	field.setAccessible(true);
    	field.set(target, userName);

    }
    
    @PostPersist
    @PostUpdate
    @PostRemove
    private void afterAnyUpdate(Object target) {
        System.out.println("[Entity AUDIT - afterAnyUpdate: ] add/update/delete complete target: \" \r\n"
        		+ target.getClass().getSuperclass().getCanonicalName());
    }
    
    @PostLoad
    private void afterLoad(Object target) {
        System.out.println("[Entity AUDIT - afterLoad: ] Entity loaded from database: " 
        		+ target.getClass().getSuperclass().getCanonicalName());
    }

}
