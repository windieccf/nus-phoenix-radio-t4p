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

package sg.edu.nus.iss.t4p.phoenix.dao.user;

import java.util.ArrayList;

import sg.edu.nus.iss.t4p.phoenix.core.dao.BaseDaoIntf;
import sg.edu.nus.iss.t4p.phoenix.entity.user.User;

public interface UserDao extends BaseDaoIntf<User>{
	
	/**
	 * getByUsername-method. This will get one or zero User object from database,
	 * which based on passed in username.
	 *
	 * @param conn
	 *            This method requires working database connection.
	 * @param username
	 *            This parameter used for retrieving User Object.
	 *            It must be individual.
	 * @return User Object for the specified program name.             
	 */
	public User getByUsername(String username);
	
	
	/**
	 * retrieveUserList-method. This will retrieve all contents from database table and build
	 * a List containing User object. Please note, that this method will
	 * consume huge amounts of resources if table has lot's of rows. This should
	 * only be used when target tables have only small amounts of data.
	 * 
	 * @param conn
	 *            This method requires working database connection.
	 * @return List of User
	 */
	public ArrayList<User> retrieveUserList();
	
	
	/**
	 * saveUser-method. This will save User information to database.  
	 * @param roleList 
	 * @param conn
	 *            This method requires working database connection.
	 * @param User
	 *            User Object to be save
	 * @return True/False to indicate whether save user is successful.
	 */
	public boolean saveUser(User user, String[] roleList);
	
	
	/**
	 * isUserExisting-method. This will check whether pass-in User exists in the database  
	 * @param conn
	 *            This method requires working database connection.
	 * @param User
	 *            User Object to be verify
	 * @return True/False to indicate whether user exists.
	 */
	public boolean isUserExisting(User user);
	
	
	/**
	 * retrievePresenterProducerList-method. This will retrieve all contents from database table and build
	 * a List containing Presenter or Producer Object.
	 * @param conn
	 *            This method requires working database connection.
	 * @param IsPresenter
	 *            True/False to indicate whether pass-in value is for Presenter or Producer
	 *            If true means Presenter,otherwise Producer
	 * @return List of Presenter or Producer
	 */
	public ArrayList<User> retrievePresenterProducerList(boolean IsPresenter);

}