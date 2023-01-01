package com.example.studentregistermanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentregistermanagement.dto.StudentDto;
import com.example.studentregistermanagement.model.Student;
import com.example.studentregistermanagement.repository.StudentRepository;

@Service
public interface StudentService {


	public StudentDto addStudent(StudentDto studentDto);

	public List<StudentDto> getAllStudents();

	public StudentDto updateStudent(Integer studentId, StudentDto student);

	public String deleteStudent(Integer studentId);
}
