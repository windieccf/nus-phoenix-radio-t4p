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
 * 20 Sep 2012    	Team 4		    1.0				Initial creating
 * 													
 * 													
 * 
 */
package sg.edu.nus.iss.t4p.phoenix.action.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.edu.nus.iss.t4p.phoenix.core.action.BaseController;
import sg.edu.nus.iss.t4p.phoenix.core.exceptions.BusinessLogicException;
import sg.edu.nus.iss.t4p.phoenix.delegate.role.RoleDelegate;
import sg.edu.nus.iss.t4p.phoenix.delegate.user.UserDelegate;
import sg.edu.nus.iss.t4p.phoenix.entity.role.Role;
import sg.edu.nus.iss.t4p.phoenix.entity.user.User;
import sg.edu.nus.iss.t4p.phoenix.utility.T4StringUtil;

/**
* Login Authentication Controller Action class 
* @author Mansoor M I A0092661A
* @version 1.0
*/
@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/userController/*")
public class UserController extends BaseController {
	
	/**
	 * Method to retrieve users and forward the request to list user page for shoing 
	 * @param request the HttpServletRequest
	 * @param response the HttpServletResponse
	 * @exception javax.servlet.ServletException
	 * @exception java.io.IOException
	 * @see javax.servlet.ServletException
	 * @see java.io.IOException
	 */
	protected void doList(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{
		// no pagination?
		List<User> users = (UserDelegate.getInstance().paginateUser(0L, 100L, new User() ));	
		request.setAttribute("users", users);
		request.getRequestDispatcher("/pages/user/list_user.jsp").forward(request, response);		
	}
	
	/**
	 * Method to initialize the currently logged in user and forward to maintain_user page
	 * for user profile maintenance.
	 * @param request the HttpServletRequest
	 * @param response the HttpServletResponse
	 * @exception javax.servlet.ServletException
	 * @exception java.io.IOException
	 * @see javax.servlet.ServletException
	 * @see java.io.IOException
	 */	
	protected void doInit(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{
		User user = new User();
		
		String userName = request.getParameter("username");
		if (userName != null) {
			user = (UserDelegate.getInstance().retrieveUser(userName));
			List<Role> roles = user.getRoles();
			for (Role role : roles) {
				request.setAttribute(""+role.getRole()+"", ""+role.getRole()+"");
			}
		} 
		
		request.setAttribute("user", user);
		
		request.getRequestDispatcher("/pages/user/maintain_user.jsp").forward(request, response);		
	}
	
	/**
	 * Method to save the changes made in the maintain_user page to database. 
	 * Method will throw exception if either User Name left empty.
	 * The user will be redirected to user listing page upon successful saving otherwise,
	 * remain in the maintain_user to re-submit the page content.
	 * @param request the HttpServletRequest
	 * @param response the HttpServletResponse
	 * @exception javax.servlet.ServletException
	 * @exception java.io.IOException
	 * @see javax.servlet.ServletException
	 * @see java.io.IOException
	 */
	protected void doSave(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{
		User user = super.retrieveParameter(request,User.class);
		boolean saveStatus = false;
		String[] roleList = request.getParameterValues("roleList");
			try {
				if(T4StringUtil.isEmpty(user.getUsername()))
					throw new BusinessLogicException("Please fill in Username");
				if(roleList.length == 0)
					throw new BusinessLogicException("Please select roles for user");
				saveStatus = (UserDelegate.getInstance().saveUser(user, roleList));
			} catch (BusinessLogicException e) {
				request.setAttribute("ERR", e.getMessage());
				request.setAttribute("user", user);
				request.getRequestDispatcher("/pages/user/maintain_user.jsp").forward(request, response);	
				e.printStackTrace();
			}
			
		if (saveStatus) {
			request.setAttribute("INF", "User saved successfully.");
			List<User> users = (UserDelegate.getInstance().paginateUser(0L, 100L, new User() ));
//			List<User> users = (UserDelegate.getInstance().retrieveUserList());
			
			request.setAttribute("users", users);
			request.getRequestDispatcher("/pages/user/list_user.jsp").forward(request, response);	
		} else {
			request.getRequestDispatcher("/pages/user/maintain_user.jsp").forward(request, response);
		}
	}
}
