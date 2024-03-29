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

package sg.edu.nus.iss.t4p.phoenix.entity.radioprogram;

import sg.edu.nus.iss.t4p.phoenix.core.annotation.Column;
import sg.edu.nus.iss.t4p.phoenix.core.annotation.Column.TYPE;
import sg.edu.nus.iss.t4p.phoenix.core.annotation.Table;
import sg.edu.nus.iss.t4p.phoenix.core.entity.BaseEntity;


@SuppressWarnings("serial")
@Table(name="RADIO_PROGRAM")
public class RadioProgram extends BaseEntity{

	/** 
     * Constructor. 
     * It takes no arguments and provides the most simple
     * way to create object instance. 
     */ 
	public RadioProgram(){}
	
	/** 
     * Persistent Instance variables. This data is directly 
     * mapped to the columns of database table.
     */
	@Column(name="ID",columnType=TYPE.PRIMARY)
	private Long id;
	
	@Column(name="PROGRAM_NAME",columnType=TYPE.ORDINARY)
	private String programName;
	
	@Column(name="PROGRAM_DESC",columnType=TYPE.ORDINARY)
	private String programDesc;
	
	@Column(name="TYPICAL_DURATION",columnType=TYPE.ORDINARY)
	private String typicalDuration;
	
	
	/**
     * Get- and Set-methods for persistent variables. The default
     * behaviour does not make any checks against malformed data,
     * so these might require some manual additions. 
     */
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	
	public String getProgramName() {return programName;}
	public void setProgramName(String programName) {this.programName = programName;}
	
	public String getProgramDesc() {return programDesc;}
	public void setProgramDesc(String programDesc) {this.programDesc = programDesc;}
	
	public String getTypicalDuration() {return typicalDuration;}
	public void setTypicalDuration(String typicalDuration) {this.typicalDuration = typicalDuration;}
}
