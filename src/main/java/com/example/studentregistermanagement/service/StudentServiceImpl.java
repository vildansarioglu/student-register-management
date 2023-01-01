package com.example.studentregistermanagement.service;

import com.example.studentregistermanagement.dto.StudentDto;
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
public class StudentServiceImpl  implements StudentService{
	@Resource
	private StudentRepository studentRepository;

	@Resource
	private CourseRepository courseRepository;

	@Transactional
	@Override
	public StudentDto addStudent(StudentDto studentDto) {
		Student student = new Student();
		mapDtoToEntity(studentDto, student);
		Student savedStudent = studentRepository.save(student);
		return mapEntityToDto(savedStudent);
	}

	@Override
	public List<StudentDto> getAllStudents() {
		List<StudentDto> studentDtos = new ArrayList<>();
		List<Student> students = studentRepository.findAll();
		students.stream().forEach(student -> {
			StudentDto studentDto = mapEntityToDto(student);
			studentDtos.add(studentDto);
		});
		return studentDtos;
	}

	@Transactional
	@Override
	public StudentDto updateStudent(Integer id, StudentDto studentDto) {
		//Student std = studentRepository.findById(id).orElse(null);
		
		 Optional<Student> student = studentRepository.findById(id);
	        if (student.isPresent()){
	            Student foundStudent=student.get();
	            foundStudent.getCourses().clear();
	        	mapDtoToEntity(studentDto, foundStudent);
	    		Student std = studentRepository.save(foundStudent);
	    		return mapEntityToDto(std);
	         }
	        else
	            return null;
	
	}

	@Override
	public String deleteStudent(Integer studentId) {
		
		Optional<Student> student = studentRepository.findById(studentId);
		//Remove the related courses from student entity.
		if(student.isPresent()) {
			student.get().removeCourses();
			studentRepository.deleteById(student.get().getId());
			return "Student with id: " + studentId + " deleted successfully!";
		}
		return null;
	}

	private void mapDtoToEntity(StudentDto studentDto, Student student) {
		student.setName(studentDto.getName());
		if (null == student.getCourses()) {
			student.setCourses(new HashSet<>());
		}
		studentDto.getCourses().stream().forEach(courseName -> {
			Course course = courseRepository.findByName(courseName);
			if (null == course) {
				course = new Course();
				course.setStudents(new HashSet<>());
			}
			course.setName(courseName);
			student.addCourse(course);
		});
	}

	private StudentDto mapEntityToDto(Student student) {
		StudentDto responseDto = new StudentDto();
		responseDto.setName(student.getName());
		responseDto.setId(student.getId());
		responseDto.setCourses(student.getCourses().stream().map(Course::getName).collect(Collectors.toSet()));
		return responseDto;
	}

}
