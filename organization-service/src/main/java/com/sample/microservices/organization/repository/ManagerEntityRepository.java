package com.sample.microservices.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.microservices.organization.model.ManagerEntity;

@Repository
public interface ManagerEntityRepository extends JpaRepository<ManagerEntity, Long>{ 
}
