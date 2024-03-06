package com.sample.microservices.uploadfiles.config.props;

import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.Data;

@Data
@ConfigurationProperties(prefix="filepermissions")
@Component
public class FilePermissionProperties {
	
	private Set<PosixFilePermission> owner;
	private Set<PosixFilePermission> group;
	private Set<PosixFilePermission> others;
	
	private Set<PosixFilePermission> all;
	
	@PostConstruct
	private void postConstruct() {
		this.all = new HashSet<>();
		this.all.addAll(this.owner);
		this.all.addAll(this.group);
		this.all.addAll(this.others);		
	}

}
