package com.gumtree.model;

import java.util.Date;
/**
 * A Person object to hold the details from the sample file
 * @author D J Baptiste
 *
 */
public class Person {
	
	private String name;
	
	private String sex;
	
	private Date dob;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

}
