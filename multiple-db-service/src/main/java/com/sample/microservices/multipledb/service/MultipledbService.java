package com.sample.microservices.multipledb.service;

import java.util.List;

import com.sample.microservices.common.model.Employee;
import com.sample.microservices.common.model.Manager;
import com.sample.microservices.multipledb.model.second.OrderEntity;
import com.sample.microservices.multipledb.model.second.UserEntity;

public interface MultipledbService {

	Employee getEmployeeById(final Long id);
    
    List<Employee> getAllEmployees();
    
    Manager getManagerById(final Long id);
    
    List<Manager> getAllManagers();
    
    UserEntity getUserById(final Long id);

    List<UserEntity> getAllUsers();

    UserEntity createUser(final UserEntity entity); 
   
    OrderEntity getOrderById(final Long id);

    List<OrderEntity> getAllOrders();

    OrderEntity createOrder(final OrderEntity entity); 
   
}
