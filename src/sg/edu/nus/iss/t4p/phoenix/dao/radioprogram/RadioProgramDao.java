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

package sg.edu.nus.iss.t4p.phoenix.dao.radioprogram;


import java.util.ArrayList;

import sg.edu.nus.iss.t4p.phoenix.core.dao.BaseDaoIntf;
import sg.edu.nus.iss.t4p.phoenix.entity.radioprogram.RadioProgram;

public interface RadioProgramDao extends BaseDaoIntf<RadioProgram>{
	
	/**
	 * retrieveProgramList-method. This will retrieve all contents from database table and build
	 * a List containing RadioProgram object. Please note, that this method will
	 * consume huge amounts of resources if table has lot's of rows. This should
	 * only be used when target tables have only small amounts of data.
	 * 
	 * @param conn
	 *            This method requires working database connection.
	 * @return List of Radio Program
	 */
	public ArrayList<RadioProgram> retrieveProgramList();
	
	/**
	 * getByProgramName-method. This will get one or zero RadioProgram object from database,
	 * which based on passed in programName.
	 *
	 * @param conn
	 *            This method requires working database connection.
	 * @param programName
	 *            This parameter used for retrieving RadioProgram Object.
	 *            It must be individual.
	 * @return Radio Program Object for the specified program name.             
	 */
	public RadioProgram getByProgramName(String programName);

}
