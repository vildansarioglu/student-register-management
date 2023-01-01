package com.example.studentregistermanagement.model;

import java.util.HashSet;
import java.util.Set;



import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "COURSE")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_sequence")
	@SequenceGenerator(name = "course_sequence", sequenceName = "course_sequence")
	private Integer id;

	@Column(name = "name")
	private String name;

	@ManyToMany(mappedBy = "courses")
	@JsonIgnore
	private Set<Student> students;
	
	
	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public void removeStudent(Student student) {
		this.getStudents().remove(student);
		student.getCourses().remove(this);
	}

	public void removeStudents() {
		for (Student student : new HashSet<>(students)) {
			removeStudent(student);
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
