package com.example.studentregistermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studentregistermanagement.model.Student;


public interface StudentRepository extends JpaRepository<Student, Integer>{
	Student findByName(String studentName);
}
