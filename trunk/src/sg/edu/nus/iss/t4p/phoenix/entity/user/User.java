/*
 * CONFIDENTIAL AND PROPRIETARY SOURCE CODE OF
 * Institute of Systems Science, National University of Singapore
 *
 * Copyright 2012 Team 4(Part-Time), ISS, NUS, Singapore. All rights reserved.
 * Use of this source code is subjected to the terms of the applicable license
 * agreement.
 *
 * -----------------------------------------------------------------
 * REVISION HISTORY
 * -----------------------------------------------------------------
 * DATE             AUTHOR          REVISION		DESCRIPTION
 * 18 Sep 2012    	Team 4		    0.1				Initial creating
 * 													
 * 													
 * 
 */
package sg.edu.nus.iss.t4p.phoenix.entity.user;

import java.util.Date;
import java.util.List;

import sg.edu.nus.iss.t4p.phoenix.core.annotation.Column;
import sg.edu.nus.iss.t4p.phoenix.core.annotation.Column.TYPE;
import sg.edu.nus.iss.t4p.phoenix.core.annotation.Table;
import sg.edu.nus.iss.t4p.phoenix.core.entity.BaseEntity;
import sg.edu.nus.iss.t4p.phoenix.entity.role.Role;

@SuppressWarnings("serial")
@Table(name="USER")
public class User extends BaseEntity{
	
	/** 
     * Constructor. 
     * It takes no arguments and provides the most simple
     * way to create object instance. 
     */ 
	public User(){}
	
	
	/** 
     * Persistent Instance variables. This data is directly 
     * mapped to the columns of database table.
     * Get- and Set-methods for persistent variables. The default
     * behaviour does not make any checks against malformed data,
     * so these might require some manual additions. 
     */
	@Column(name="ID",columnType=TYPE.PRIMARY)
	private Long id;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}

	@Column(name="USERNAME",columnType=TYPE.ORDINARY)
	private String username;
	public String getUsername() {return username;}
	public void setUsername(String username) {this.username = username;}
	
	@Column(name="PASSWORD",columnType=TYPE.ORDINARY)
	private String password;
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
	
	@Column(name="FIRST_NAME",columnType=TYPE.ORDINARY)
	private String firstName;
	public String getFirstName() {return firstName;}
	public void setFirstName(String firstName) {this.firstName = firstName;}
		
	@Column(name="LAST_NAME",columnType=TYPE.ORDINARY)
	private String lastName;
	public String getLastName() {return lastName;}
	public void setLastName(String lastName) {this.lastName = lastName;}
	
	@Column(name="DOB",columnType=TYPE.ORDINARY)
	private Date DateOfBirth;
	public Date getDateOfBirth() {return DateOfBirth;}
	public void setDateOfBirth(Date dateOfBirth) {DateOfBirth = dateOfBirth;}
	
	@Column(name="EMAIL",columnType=TYPE.ORDINARY)
	private String email;
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	
	@Column(name="ADDRESS",columnType=TYPE.ORDINARY)
	private String address;
	public String getAddress() {return address;}
	public void setAddress(String address) {this.address = address;}
	
	@Column(name="CONTACT_HOME",columnType=TYPE.ORDINARY)
	private String contactHome;
	public String getContactHome() {return contactHome;}
	public void setContactHome(String contactHome) {this.contactHome = contactHome;}
	
	@Column(name="CONTACT_MOBILE",columnType=TYPE.ORDINARY)
	private String contactMobile;
	public String getContactMobile() {return contactMobile;}
	public void setContactMobile(String contactMobile) {this.contactMobile = contactMobile;}
	
	@Column(name="JOIN_DATE",columnType=TYPE.ORDINARY)
	private Date joinDate;
	public Date getJoinDate() {return joinDate;}
	public void setJoinDate(Date joinDate) {this.joinDate = joinDate;}
	
	private List<Role> roles;
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	
	
}
