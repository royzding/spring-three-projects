package com.sample.microservices.multipledb.service;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.microservices.common.model.Employee;
import com.sample.microservices.common.model.Manager;
import com.sample.microservices.multipledb.map.EmployeeMapper;
import com.sample.microservices.multipledb.map.ManagerMapper;
import com.sample.microservices.multipledb.model.first.ManagerEntity;
import com.sample.microservices.multipledb.model.second.OrderEntity;
import com.sample.microservices.multipledb.model.second.UserEntity;
import com.sample.microservices.multipledb.repository.first.EmployeeEntityRepository;
import com.sample.microservices.multipledb.repository.first.ManagerEntityRepository;
import com.sample.microservices.multipledb.repository.second.OrderEntityRepository;
import com.sample.microservices.multipledb.repository.second.UserEntityRepository;

@Service
public class MultipledbServiceImpl implements MultipledbService {

	private final EmployeeMapper empMapper;
	private final EmployeeEntityRepository empRepository;
	
	private final ManagerMapper manMapper;    
	private final ManagerEntityRepository manRepository;
	
	private final UserEntityRepository userEntityRepository;
	private final OrderEntityRepository orderEntityRepository;	
    
	MultipledbServiceImpl(EmployeeMapper empMapper, EmployeeEntityRepository empRepository, 
			ManagerMapper manMapper, ManagerEntityRepository manRepository, 
			UserEntityRepository userEntityRepository, OrderEntityRepository orderEntityRepository) {
		this.empMapper = Mappers.getMapper(EmployeeMapper.class);
		this.empRepository = empRepository;
		this.manMapper = Mappers.getMapper(ManagerMapper.class);
		this.manRepository = manRepository;
		this.userEntityRepository = userEntityRepository;
		this.orderEntityRepository = orderEntityRepository;
	}

	@Override
	public Employee getEmployeeById(Long id) {
		return this.empMapper.entityToEmployee(this.empRepository.findById(id).get());
	}

	@Override
	public List<Employee> getAllEmployees() {
		return this.empMapper.entityToEmployee(this.empRepository.findAll());
	}
	
	@Override
	public Manager getManagerById(final Long id) {				
		return this.manMapper.entityToManager(this.manRepository.findById(id).get());
	}

	@Override
	@Cacheable("all-managers")
	public List<Manager> getAllManagers() {
		
		List<ManagerEntity> entities = this.manRepository.findAll();
		
		return this.manMapper.entityToManager(entities);
	}

	@Override
	public UserEntity getUserById(Long id) {
		return this.userEntityRepository.findById(id).get();
	}

	@Override
	public List<UserEntity> getAllUsers() {
		return this.userEntityRepository.findAll();
	}

	@Override
	@Transactional
	public UserEntity createUser(final UserEntity entity) {

		entity.setId(null);
		this.userEntityRepository.save(entity);
		
		return this.userEntityRepository.save(entity);
	}

	@Override
	public OrderEntity getOrderById(Long id) {
		return this.orderEntityRepository.findById(id).get();
	}

	@Override
	public List<OrderEntity> getAllOrders() {
		return this.orderEntityRepository.findAll();
	}

	@Override
	public OrderEntity createOrder(OrderEntity entity) {
		entity.setId(null);
		this.orderEntityRepository.save(entity);
		
		return this.orderEntityRepository.save(entity);
	}


}
