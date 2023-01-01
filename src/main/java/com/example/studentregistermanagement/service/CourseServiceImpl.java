package com.example.studentregistermanagement.service;

import com.example.studentregistermanagement.dto.CourseDto;
import com.example.studentregistermanagement.model.Course;
import com.example.studentregistermanagement.model.Student;
import com.example.studentregistermanagement.repository.CourseRepository;
import com.example.studentregistermanagement.repository.StudentRepository;

import jakarta.annotation.Resource;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CourseServiceImpl implements CourseService{
	@Resource
	private StudentRepository studentRepository;

	@Resource
	private CourseRepository courseRepository;

	@Transactional
	@Override
	public CourseDto addCourse(CourseDto courseDto) {
		Course course = new Course();
		mapDtoToEntity(courseDto, course);
		Course savedCourse = courseRepository.save(course);
		return mapEntityToDto(savedCourse);
	}

	@Override
	public List<CourseDto> getAllCourses() {
		List<CourseDto> courseDtos = new ArrayList<>();
		List<Course> courses = courseRepository.findAll();
		courses.stream().forEach(course -> {
			CourseDto courseDto = mapEntityToDto(course);
			courseDtos.add(courseDto);
		});
		return courseDtos;
	}

	@Transactional
	@Override
	public CourseDto updateCourse(Integer id, CourseDto courseDto) {
		Course crs = courseRepository.findById(id).orElse(null);
		crs.getStudents().clear();
		mapDtoToEntity(courseDto, crs);
		Course course = courseRepository.save(crs);
		return mapEntityToDto(course);
	}

	@Transactional
	@Override
	public String deleteCourse(Integer id) {
		Optional<Course> course = courseRepository.findById(id);
		// Remove the related students from course entity.
		if(course.isPresent()) {
			course.get().removeStudents();
			courseRepository.deleteById(course.get().getId());
			return "Course with id: " + id + " deleted successfully!";
		}
		return null;
	}

	private void mapDtoToEntity(CourseDto courseDto, Course course) {
		course.setName(courseDto.getName());
		if (null == course.getStudents()) {
			course.setStudents(new HashSet<>());
		}
		courseDto.getStudents().stream().forEach(studentName -> {
			Student student = studentRepository.findByName(studentName);
			if (null == student) {
				student = new Student();
				student.setCourses(new HashSet<>());
			}
			student.setName(studentName);
			student.addCourse(course);
		});
	}

	private CourseDto mapEntityToDto(Course course) {
		CourseDto responseDto = new CourseDto();
		responseDto.setName(course.getName());
		responseDto.setId(course.getId());
		responseDto.setStudents(course.getStudents().stream().map(Student::getName).collect(Collectors.toSet()));
		return responseDto;
	}
}
