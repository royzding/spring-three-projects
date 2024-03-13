package com.sample.microservices.department.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sample.microservices.common.annotation.LoggableType;
import com.sample.microservices.department.data.model.Student;
import com.sample.microservices.department.repository.StudentRepository;

@Service
@LoggableType
public class StudentServiceImpl implements StudentService {
	
	private final StudentRepository repository;
		
	public StudentServiceImpl(StudentRepository repository) {
		this.repository = repository;
	}

	@Override
	public Student createStudent(Student student) {
		return this.repository.save(student);
	}

	@Override
	public Student getStudentById(Long id) {
		return this.repository.findById(id).get();
	}

	@Override
	public void updateStudent(Long id, Student student) throws Exception {
		
		student.setId(id);
		
		this.repository.save(student);
		
	}

	@Override
	public void deleteStudentById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Student> createStudents(List<Student> students) {
		
		List<Student> stList = new ArrayList<>();
		
		this.repository.saveAll(students).forEach(stList::add);
		
		return stList;
	}

	@Override
	public List<Student> getAllStudents() {
		
		List<Student> stList = new ArrayList<>();
		
		this.repository.findAll().forEach(stList::add);
		
		return stList;
	}
	
	
}
