package com.example.studentregistermanagement.service;

import java.util.List;

import com.example.studentregistermanagement.dto.CourseDto;

public interface CourseService {

	public CourseDto updateCourse(Integer id, CourseDto course);

	public String deleteCourse(Integer id);

	public CourseDto addCourse(CourseDto courseDto);

	public List<CourseDto> getAllCourses();
}
