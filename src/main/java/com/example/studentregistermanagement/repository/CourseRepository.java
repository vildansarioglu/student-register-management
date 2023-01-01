package com.example.studentregistermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.studentregistermanagement.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>{
	public Course findByName(String courseName);
}
