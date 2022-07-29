package com.luv2code.hibernate.demo.entity;

import java.util.Date;

// java persistence API(standard interface that hibernate implements)
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.luv2code.hibernate.demo.DateUtils;

@Entity // class mapped to table
@Table(name="student")
public class Student {
	
	// field set up & map fields to database columns
	@Id //primary key �߰�
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id") // database�� � column�� mapping�Ұ�����
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;

	@Column(name="date_of_birth")
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
	public Student() {
		
	}
	// �ʼ��� �ƴ�����  ���߿� ����� ���� constructor�� ����� �ش�. id�� üũ���ϴ� ������ ���߿� �Ȼ���Ұ��̱� ������.
	
	public Student( String firstName, String lastName, String email, Date theDateOfBirth) {
		this.firstName = firstName;
		this.lastName = lastName;
	    this.email = email;
	    this.dateOfBirth = theDateOfBirth;    
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
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	@Override
	  public String toString() {
	       return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
	                + ", dateOfBirth=" + DateUtils.formatDate(dateOfBirth) + "]";
	}
	
}
