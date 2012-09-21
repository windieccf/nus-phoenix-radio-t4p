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

package sg.edu.nus.iss.t4p.phoenix.service.user;

import java.sql.SQLException;
import java.util.List;

import sg.edu.nus.iss.t4p.phoenix.core.dao.DaoFactory;
import sg.edu.nus.iss.t4p.phoenix.core.exceptions.BusinessLogicException;
import sg.edu.nus.iss.t4p.phoenix.entity.user.User;

public class UserService {
	
	/** 
     * Constructor. 
     * It takes no arguments and provides the most simple
     * way to create object instance. 
     */
	public static UserService instance;
	private UserService(){/*SINGELTON*/}
	
	/**
	 * getInstance-method. This method is used to create a new RadioProgramService
	 * object if it does not exists. 
	 */
	public static UserService getInstance(){
		if(instance == null)
			instance = new UserService();
		
		return instance;
	}
	
	
	/**
	 * retrieveUserList-method. This will retrieve a list of User object.
	 * Please note, that this method will consume huge amounts of resources if table has lot's of rows.
	 * This should only be used when target tables have only small amounts of data.
	 */
	public List<User> retrieveUserList()  {
		List<User> users = null;
		try {
			users = DaoFactory.getInstance().getUserDao().loadAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
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
	public List<User> paginateUser(Long pageNo , Long rowPerPage, User user)  {
		List<User> users = null;
		try {
			users = DaoFactory.getInstance().getUserDao().paginate(pageNo, rowPerPage,  user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
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
		User user = DaoFactory.getInstance().getUserDao().getByUsername(username);
		try {
			user.setRoles(DaoFactory.getInstance().getRoleDao().getRolesByUserId(user.getId()) );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		 // user
		
		return user;
	}
	
	
	/**
	 * saveUser-method. This will save User information to database.  
	 * 
	 * @param User
	 *            User Object to be save
	 * @return True/False to indicate whether save user is successful.
	 */
	public boolean saveUser(User user) throws BusinessLogicException {
		if(!user.isPkSet() && DaoFactory.getInstance().getUserDao().isUserExisting(user))
			throw new BusinessLogicException("Duplicate user name, please amend.");
		
		boolean saveStatus = DaoFactory.getInstance().getUserDao().saveUser(user);	
		return saveStatus;
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
		List<User> presenterproducers = null;
		try{
			presenterproducers = DaoFactory.getInstance().getUserDao().retrievePresenterProducerList(IsPresenter);
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return presenterproducers;
	}
    
    /**
	 * isUserExisting-method. This will check whether pass-in User exists  
	 * 
	 * @param User
	 *            User Object to be verify
	 * @return True/False to indicate whether user exists.
	 */
	public boolean isUserExisting(User user) {
		return DaoFactory.getInstance().getUserDao().isUserExisting(user);	
	}
}
