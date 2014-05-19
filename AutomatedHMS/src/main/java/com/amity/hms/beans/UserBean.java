package com.amity.hms.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
@Entity
@Table(name="TUSER")
public class UserBean {
	
	@Id
	@Column(name="enrollment_number")
	private String enrollNo;
	
	@Column(name="first_name", nullable=false)
	private String firstName;
	
	@Column(name="middle_name", nullable=true)
	private String middleName;
	
	@Column(name="last_name", nullable=false)
	private String lastName;
	
	private String pass;
	
	private String confirmPass;
	
	@Column(name="dob", nullable=false)
	private String dob;
	
	@Column(name="student_email", nullable=false)
	private String studentEmail;
	
	@Column(name="home_phone", nullable=true)
	private String homePhone;
	
	@Column(name="student_mobile", nullable=false)
	private String studentMobile;
	
	@Column(name="parent_mobile", nullable=true)
	private String parentMobile;
	
	@Column(name="parent_email", nullable=false)
	private String parentEmail;
	
	@Column(name="address", nullable=false)
	private String homeAddress;
	
	public String getEnrollNo() {
		return enrollNo;
	}
	public void setEnrollNo(String enrollNo) {
		this.enrollNo = enrollNo;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getConfirmPass() {
		return confirmPass;
	}
	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	public String getStudentMobile() {
		return studentMobile;
	}
	public void setStudentMobile(String studentMobile) {
		this.studentMobile = studentMobile;
	}
	public String getParentMobile() {
		return parentMobile;
	}
	public void setParentMobile(String parentMobile) {
		this.parentMobile = parentMobile;
	}
	public String getParentEmail() {
		return parentEmail;
	}
	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}
	public String getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}	
}
