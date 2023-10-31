package com.sample.microservices.common.model;

import java.util.Set;

import lombok.Data;


@Data
public class UserDetailStore {

    private String username;
    private String name;
    private String email;
    private Set<Role> roles;
    
    public void clear() {
    	this.setUsername(null);
    	this.setName(null);
    	this.setEmail(null);
    	this.setRoles(null);    	
    }

    public void copyData(UserDetailStore userDetailStore) {
    	this.setUsername(userDetailStore.username);
    	this.setName(userDetailStore.name);
    	this.setEmail(userDetailStore.email);
    	this.setRoles(userDetailStore.roles);
    }

	@Override
	public String toString() {
		return "UserDetailStore [username=" + username + ", name=" + name + ", email=" + email + ", roles=" + roles
				+ "]";
	}
	
}