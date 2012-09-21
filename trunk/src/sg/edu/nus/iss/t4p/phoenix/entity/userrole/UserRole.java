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
 * 20 Sep 2012    	Team 4		    0.1				Initial creating
 * 													
 * 													
 * 
 */

package sg.edu.nus.iss.t4p.phoenix.entity.userrole;

import sg.edu.nus.iss.t4p.phoenix.core.annotation.Column;
import sg.edu.nus.iss.t4p.phoenix.core.annotation.Column.TYPE;
import sg.edu.nus.iss.t4p.phoenix.core.annotation.Table;
import sg.edu.nus.iss.t4p.phoenix.core.entity.BaseEntity;


@SuppressWarnings("serial")
@Table(name="USER_ROLE")
public class UserRole extends BaseEntity{

	/** 
     * Constructor. 
     * It takes no arguments and provides the most simple
     * way to create object instance. 
     */ 
	public UserRole(){}
	
	/** 
     * Persistent Instance variables. This data is directly 
     * mapped to the columns of database table.
     */
	@Column(name="ID",columnType=TYPE.PRIMARY)
	private Long id;
	
	@Column(name="USER_ID",columnType=TYPE.ORDINARY)
	private String userId;
	
	@Column(name="ROLE_ID",columnType=TYPE.ORDINARY)
	private String roleId;
	
	
	/**
     * Get- and Set-methods for persistent variables. The default
     * behaviour does not make any checks against malformed data,
     * so these might require some manual additions. 
     */
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	
	public String getUserId() {return userId;}
	public void setuserId(String userId) {this.userId = userId;}
	
	public String getroleId() {return roleId;}
	public void setroleId(String roleId) {this.roleId = roleId;}
	
}