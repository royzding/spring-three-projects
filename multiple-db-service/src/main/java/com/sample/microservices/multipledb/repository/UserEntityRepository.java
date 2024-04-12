package com.sample.microservices.multipledb.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.microservices.multipledb.model.UserEntity;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {
}
