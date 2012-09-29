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

package sg.edu.nus.iss.t4p.phoenix.delegate.user;

import java.util.List;

import sg.edu.nus.iss.t4p.phoenix.core.exceptions.BusinessLogicException;
import sg.edu.nus.iss.t4p.phoenix.entity.user.User;
import sg.edu.nus.iss.t4p.phoenix.service.user.UserService;

public class UserDelegate {
	
	/** 
     * Constructor. 
     * It takes no arguments and provides the most simple
     * way to create object instance. 
     */
	public static UserDelegate instance;
	private UserDelegate(){/*SINGLETON*/}
	
	
	/**
	 * getInstance-method. This method is used to create a new UserDelegate
	 * object if it does not exists. 
	 */
	public static UserDelegate getInstance(){
		if(instance == null)
			instance = new UserDelegate();
		
		return instance;
	}
	
	
	/**
	 * retrieveUserList-method. This will retrieve a list of User object.
	 * Please note, that this method will consume huge amounts of resources if table has lot's of rows.
	 * This should only be used when target tables have only small amounts of data.
	 */
	public List<User> retrieveUserList() {
		return (UserService.getInstance().retrieveUserList());
	}
	
	
	/**
	 * paginateUser-method. This will get a list of User Object based on the page count and row per page
	 * 
	 * @param pageNo
	 *            Page Number
	 * @param rowPerPage
	 *            Row number per Page
	 * @param user
	 *            User Object
	 * @return 
	 *            List of User
	 */
	public List<User> paginateUser(Long pageNo , Long rowPerPage, User user) {
		return (UserService.getInstance().paginateUser(pageNo , rowPerPage , user));
	}
	
	
	/**
	 * retrieveUser-method. This will get one or zero User object based on passing in username.
	 *
	 * @param username
	 *            This parameter used for retrieving User Object.
	 *            It must be individual.  
	 * @return User Object based on pass-in username           
	 */
	public User retrieveUser(String username) {
		return (UserService.getInstance().retrieveUser(username));
	}
	
	
	/**
	 * saveUser-method. This will save User information to database.  
	 * 
	 * @param user
	 * @param roleList
	 * @return True/False to indicate whether save user is successful.
	 * @throws BusinessLogicException
	 */
	public boolean saveUser(User user) throws BusinessLogicException {
		return (UserService.getInstance().saveUser(user));
	}
	
	
	/**
	 * retrievePresenterProducerList-method. This will retrieve a list of Presenter or Producer Object.
	 * 
	 * @param IsPresenter
	 *            True/False to indicate whether pass-in value is for Presenter or Producer
	 *            If true means Presenter,otherwise Producer
	 * @return List of Presenter or Producer
	 */
	public List<User> retrievePresenterProducerList(boolean IsPresenter){
		return UserService.getInstance().retrievePresenterProducerList(IsPresenter);
	}

}
