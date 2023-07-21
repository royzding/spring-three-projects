package com.sample.microservices.employee.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.sample.microservices.common.model.Manager;
import com.sample.microservices.common.pagination.PageLayout;
import com.sample.microservices.employee.enums.ManagerSortType;
import com.sample.microservices.employee.model.dto.ManagerDto;
import com.sample.microservices.employee.model.dto.ManagerMini;

public interface ManagerService {

    Manager getManagerById(final Long id);
    
    List<Manager> getAllManagers();
    
    PageLayout<Manager> getAllManagersWithPaginationAndContaining(String name, int pageNum, int pageSize, 
			List<ManagerSortType> sort, Direction direction);
    
    PageLayout<Manager> getAllManagersWithPaginationAndFilter(List<String> names, 
    		int pageNum, int pageSize, List<ManagerSortType> sort, Sort.Direction direction);
       
    Manager createManager(final ManagerDto managerDto);
    
    List<Manager> createManagers(final List<ManagerDto> managerDto);
    
    void deleteManagerById(final Long id);
    
    void deleteAllManagers();
    
	void deleteManagersByName(String name);
    void deleteManagersByNameLike(String name);
    
    void updateManager(final Long id, final ManagerDto managerDto)  throws Exception;
    
    String getManagerMaxSalary(String name);
    
    void zeroInParamPr();
    void insertManagerBk(Long id);
    void twoInParamPr(Long id, Double salaryInc);
    String getSalaryById(Long id);
    
    List<Manager> getManagersByName1(String name);
    List<Manager> getManagersByName2(String name);
    List<Manager> getManagersByName3(String name);
    
    List<Manager> getManagersByNameIn1(List<String> names);
    List<Manager> getManagersByNameIn2(List<String> names);
    
    List<Manager> getManagersByNameLike(String name);
    List<Manager> getManagersByNameLikeNative(String name);
    
    List<ManagerMini> getManagerMiniByNameLikeNative(String name);
}
