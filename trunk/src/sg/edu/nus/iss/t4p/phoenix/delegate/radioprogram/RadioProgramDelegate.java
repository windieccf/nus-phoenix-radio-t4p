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

package sg.edu.nus.iss.t4p.phoenix.delegate.radioprogram;

import java.util.List;

import sg.edu.nus.iss.t4p.phoenix.entity.radioprogram.RadioProgram;
import sg.edu.nus.iss.t4p.phoenix.service.radioprogram.RadioProgramService;

public class RadioProgramDelegate {
	
	/** 
     * Constructor. 
     * It takes no arguments and provides the most simple
     * way to create object instance. 
     */
	public static RadioProgramDelegate instance;
	private RadioProgramDelegate(){
		/*SINGLETON*/
	}
	
	
	/**
	 * getInstance-method. This method is used to create a new RadioProgramDelegate
	 * object if it does not exists. 
	 */
	public static RadioProgramDelegate getInstance(){
		if(instance == null)
			instance = new RadioProgramDelegate();
		
		return instance;
	}
	
	/**
	 * retrieveProgramList-method. This will retrieve a list of RadioProgram object.
	 * Please note, that this method will consume huge amounts of resources if table has lot's of rows.
	 * This should only be used when target tables have only small amounts of data.
	 * 
	 * @return List of Radio Program
	 */
	public List<RadioProgram> retrieveProgramList() {
		return (RadioProgramService.getInstance().retrieveProgramList());
	}
	
	
	/**
	 * getByProgramName-method. This will get one or zero RadioProgram object based on passing in programName.
	 *
	 * param programName
	 *            This parameter used for retrieving RadioProgram Object.
	 *            It must be individual.
	 * @return Radio Program Object based on pass-in programName             
	 */
	public RadioProgram getByProgramName(String programName) {
		return (RadioProgramService.getInstance().getByProgramName(programName));
	}
}
