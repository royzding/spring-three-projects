package com.sample.microservices.department.service;

import java.util.List;

import com.sample.microservices.department.data.model.Student;

public interface StudentService {

    Student createStudent(final Student Student);

    List<Student> createStudents(final List<Student> Students);

    Student getStudentById(final Long id);
    
    void updateStudent(final Long id, final Student Student)  throws Exception;

    void deleteStudentById(final Long id);
    
    List<Student> getAllStudents();
    
}
