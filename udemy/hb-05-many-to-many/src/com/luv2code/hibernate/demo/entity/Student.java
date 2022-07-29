package com.luv2code.hibernate.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
// java persistence API(standard interface that hibernate implements)
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity // class mapped to table
@Table(name="student")
public class Student {
	
	// field set up & map fields to database columns
	@Id //primary key 추가
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id") // database의 어떤 column과 mapping할것인지
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@ManyToMany(fetch = FetchType.LAZY,	 cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
			name = "course_student",
			joinColumns=@JoinColumn(name = "student_id"),
			inverseJoinColumns=@JoinColumn(name = "course_id")
			)
	private List<Course> courses;

	public Student() {
		
	}
	// 필수는 아니지만  나중에 사용할 편리한 constructor를 만들어 준다. id를 체크안하는 이유는 나중에 안사용할것이기 때문에.
	public Student(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	// generate getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	// generate toString(for debugging purposes)
	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}	
	
}
