package com.example.demo.bean;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="userdetails")
public class User {
	
@Id
@Column(name="id")
@GeneratedValue(strategy=GenerationType.AUTO)

	private int id;
	@Column(name="firstname")
	private String firstname;	

	@Column(name="lastname")
	private String lastname;

	@Column(name="pincode")
	private int pincode;

	@Column(name="isActive", nullable=true)
	private boolean isActive;

	@Column(name="dateofbirth")
	private Date dateofbirth;

	@Column(name="email")
	private String email;
	
	public User() {
		
	}
	
	public User(int id, String firstname, String lastname, int pincode, boolean isActive, Date dateofbirth) {
		
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.pincode = pincode;
		this.isActive = isActive;
		this.dateofbirth = dateofbirth;
		this.email=email;
		
	}
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstname;
	}
	public void setFirstName(String firstname) {
		this.firstname = firstname;
	}
	public String getLastName() {
		return lastname;
	}
	public void setLastName(String lastname) {
		this.lastname = lastname;
	}
	public int getPinCode() {
		return pincode;
	}
	public void setPinCode(int pincode) {
		this.pincode = pincode;
	}
	public boolean getisActive() {
		return isActive;
	}
	public void setisActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getDob() {
		//Date myDate = new Date();
		//return dateofbirth;
		return new SimpleDateFormat("yyyy-MM-dd").format(dateofbirth);
	}
	public void setDob(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}


}
