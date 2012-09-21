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

package sg.edu.nus.iss.t4p.phoenix.dao.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sg.edu.nus.iss.t4p.phoenix.core.constant.ConstantStatus;
import sg.edu.nus.iss.t4p.phoenix.core.dao.impl.BaseDao;
import sg.edu.nus.iss.t4p.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.t4p.phoenix.dao.user.UserDao;
import sg.edu.nus.iss.t4p.phoenix.entity.role.Role;
import sg.edu.nus.iss.t4p.phoenix.entity.user.User;
import sg.edu.nus.iss.t4p.phoenix.entity.userrole.UserRole;

public class UserDaoImpl extends BaseDao<User> implements UserDao{
	
	/** 
     * Constructor. 
     * It uses default constructor from super class.
     */
	private static UserDaoImpl instance;
	private UserDaoImpl(){
		/*ENSURE SINGLETON*/
		super();
	}
	
	/**
	 * getInstance-method. This method is used to create a new UserDaoImpl
	 * object if it does not exists. 
	 */
	public static UserDao getInstance(){
		if(instance == null)
			instance = new UserDaoImpl();
		
		return instance;
	}
    
	
	/* (non-Javadoc)
	 * @see package sg.edu.nus.iss.t4p.phoenix.dao.user#getByUsername()
	 */
	@Override
	public User getByUsername(String username) {
		
		try( Connection con = super.getConnection() ){
			StringBuffer mySql = new StringBuffer("SELECT * FROM ")
								.append( super.getTableName(User.class.getName()) + " WHERE UPPER(USERNAME) = ? ");

			PreparedStatement stmt = con.prepareStatement(mySql.toString());
			stmt.setString(1, username.toUpperCase());

			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				User user = super.createValueObject();
				super.assignValue(user, user.getColumnField(), rs);
				return user;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/* (non-Javadoc)
	 * @see package sg.edu.nus.iss.t4p.phoenix.dao.user#retrieveUserList()
	 */
	@Override
	public ArrayList<User> retrieveUserList() {
		ArrayList<User> users = new ArrayList<>();
		try( Connection con = super.getConnection() ){
			StringBuffer mySql = new StringBuffer("SELECT * FROM ")
								.append( super.getTableName(User.class.getName()));

			PreparedStatement stmt = con.prepareStatement(mySql.toString());
	
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				User user = super.createValueObject();
				super.assignValue(user, user.getColumnField(), rs);
				users.add(user);
			}
			return users;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}

	
	/* (non-Javadoc)
	 * @see package sg.edu.nus.iss.t4p.phoenix.dao.user#saveUser()
	 */
	@Override
	public boolean saveUser(User user) {
		try {
			super.persist(user);
		} catch (SQLException | NotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	/* (non-Javadoc)
	 * @see package sg.edu.nus.iss.t4p.phoenix.dao.user#isUserExisting()
	 */
	@Override
	public boolean isUserExisting(User user) {
		try( Connection con = super.getConnection() ){
			StringBuffer mySql = new StringBuffer("SELECT COUNT(*) AS COUNTER FROM ")
			.append( super.getTableName(User.class.getName()) + " WHERE UPPER(USERNAME) = ? and STATUS = '" + ConstantStatus.ACTIVE +"' ");

			PreparedStatement stmt = con.prepareStatement(mySql.toString());
			stmt.setString(1, user.getUsername().toUpperCase());
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				if (rs.getInt("COUNTER") > 0) return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	
	/* (non-Javadoc)
	 * @see package sg.edu.nus.iss.t4p.phoenix.dao.user#retrievePresenterProducerList()
	 */
	@Override
	public ArrayList<User> retrievePresenterProducerList(boolean IsPresenter){
		ArrayList<User> users = new ArrayList<>();
		try( Connection con = super.getConnection() ){
			String mySql = "SELECT * FROM " + super.getTableName(User.class.getName()) + " U" +
					       " WHERE 1=1 " + 
					       "AND U.ID = (" +
					       " SELECT DISTINCT(UR.USER_ID) FROM " + super.getTableName(UserRole.class.getName()) + " UR " +
					       "   INNER JOIN " + super.getTableName(Role.class.getName()) + " R ON R.ID = UR.ROLE_ID " +
		                   "WHERE 1=1 " +
					       " AND UR.USER_ID = U.ID " + 
		                   " AND R.ROLE = ? )" ;

			PreparedStatement stmt = con.prepareStatement(mySql.toString());
			
			if(IsPresenter == true){
	           stmt.setString(1, "Presenter");
			}
			else{
				stmt.setString(1, "Producer");
			}
	        
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				User user = super.createValueObject();
				super.assignValue(user, user.getColumnField(), rs);
				users.add(user);
			}
			return users;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}

}
