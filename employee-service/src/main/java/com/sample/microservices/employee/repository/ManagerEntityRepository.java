package com.sample.microservices.employee.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sample.microservices.common.model.Manager;
import com.sample.microservices.employee.model.dao.ManagerEntity;
import com.sample.microservices.employee.model.dto.IManagerMini;

@Repository
public interface ManagerEntityRepository extends JpaRepository<ManagerEntity, Long>{
	
	List<ManagerEntity> findByName(String name);
	List<ManagerEntity> findByNameIn(List<String> names);
	List<ManagerEntity> findByNameContainingIgnoreCase(String name, Sort sort);
	
	Page<ManagerEntity> findByNameIn(List<String> names, Pageable pageable);
	
    @Procedure(procedureName = "get_salary_by_name", outputParameterName = "out_salary")
    Double getSalaryByName(@Param("in_name") String name);
    
    @Procedure(procedureName = "zero_in_param_prx")
    void zeroInParamPr();
	
    @Procedure(procedureName = "insert_manager_bk")
    void insertManagerBk(@Param("in_manager_id") Long id);
	
    @Procedure(procedureName = "two_in_param_pr")
    void twoInParamPr(@Param("in_manager_id") Long id, @Param("in_salary_inc") Double salaryInc);
    
    //calling an oracle function get_salary_by_id(in_id IN NUMBER)
    @Query(nativeQuery = true, value = "SELECT get_salary_by_id(:id) FROM dual")
    Double getSalaryById(@Param("id") Long id);
    
    
    @Query("SELECT m FROM ManagerEntity m where m.name = :name")
    List<ManagerEntity> getManagersByName1(@Param("name") String name);
    
    @Query("SELECT m FROM ManagerEntity m where m.name = ?1")
    List<ManagerEntity> getManagersByName2(String name);
    
    @Query("SELECT new com.sample.microservices.common.model.Manager(m.name, m.salary) FROM ManagerEntity m where m.name = :name")
    List<Manager> getManagersByName3(@Param("name") String name);
    
    @Query("SELECT m FROM ManagerEntity m where m.name in :names")
    List<ManagerEntity> getManagersByNameIn1(@Param("names") List<String> names);
    
    @Query("SELECT m FROM ManagerEntity m where m.name in ?1")
    List<ManagerEntity> getManagersByNameIn2(List<String> names);
    
    @Query("SELECT m FROM ManagerEntity m where m.name like %?1%")
    List<ManagerEntity> getManagersByNameLike(@Param("name") String name);
    
    @Query(nativeQuery = true, value = "SELECT * from manager m WHERE m.name like %:name% ")
    List<ManagerEntity> getManagersByNameLikeNative(@Param("name") String name);
    
    @Modifying
    @Query(nativeQuery = true, value = "DELETE from manager m WHERE m.name = :name ")
    void deleteManagersByName(@Param("name") String name);
    
    @Modifying
    @Query(nativeQuery = true, value = "DELETE from manager m WHERE m.name like %:name% ")
    void deleteManagersByNameLike(@Param("name") String name);
    
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE manager m SET m.salary = :salary WHERE m.name = :name ")
    void updateManagerSalaryByName(@Param("salary") Double salary, @Param("name") String name);
    
    //using interface to convert native query
    @Query(nativeQuery = true, value = "SELECT id, name, salary, modified_by as modifiedBy from manager m WHERE m.name like %:name% ")
    List<IManagerMini> getManagerMiniByNameLikeNative(@Param("name") String name);
    
    @Query(nativeQuery = true, value = "SELECT MAX(salary) from manager ")
    Optional<Double> getMaxSalary();
    
    
}
