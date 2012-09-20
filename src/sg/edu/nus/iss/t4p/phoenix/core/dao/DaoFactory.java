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
 * 15 Sep 2012    	Team 4		    0.1				Initial creating
 * 													
 * 													
 * 
 */
package sg.edu.nus.iss.t4p.phoenix.core.dao;

import sg.edu.nus.iss.t4p.phoenix.dao.programslot.ProgramSlotDao;
import sg.edu.nus.iss.t4p.phoenix.dao.programslot.impl.ProgramSlotDaoImpl;
import sg.edu.nus.iss.t4p.phoenix.dao.role.RoleDao;
import sg.edu.nus.iss.t4p.phoenix.dao.role.impl.RoleDaoImpl;
import sg.edu.nus.iss.t4p.phoenix.dao.user.UserDao;
import sg.edu.nus.iss.t4p.phoenix.dao.user.impl.UserDaoImpl;
import sg.edu.nus.iss.t4p.phoenix.dao.radioprogram.RadioProgramDao;
import sg.edu.nus.iss.t4p.phoenix.dao.radioprogram.impl.RadioProgramDaoImpl;

public class DaoFactory {
	
	/** SINGLETON ******************************/
	private static DaoFactory instance;
	private DaoFactory(){/*ENSURE SINGLETON*/
		this.userDao = UserDaoImpl.getInstance();
		this.programSlotDao = ProgramSlotDaoImpl.getInstance();
		this.roleDao = RoleDaoImpl.getInstance();
		this.radioProgramDao = RadioProgramDaoImpl.getInstance();
	}
	public static DaoFactory getInstance(){
		if(instance == null)
			instance = new DaoFactory();
		
		return instance;
	}
	
	/** VARIABLES ******************************/
	private UserDao userDao;
	public UserDao getUserDao(){return this.userDao;}
	
	private ProgramSlotDao programSlotDao;
	public ProgramSlotDao getProgramSlotDao(){return this.programSlotDao;}
	
	private RoleDao roleDao;
	public RoleDao getRoleDao(){ return this.roleDao;}
	
	private RadioProgramDao radioProgramDao;
	public RadioProgramDao getRadioProgramDao(){return this.radioProgramDao;}
}
