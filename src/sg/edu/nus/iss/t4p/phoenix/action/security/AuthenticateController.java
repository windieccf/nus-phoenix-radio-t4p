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
package sg.edu.nus.iss.t4p.phoenix.action.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.edu.nus.iss.t4p.phoenix.core.action.BaseController;
import sg.edu.nus.iss.t4p.phoenix.core.constant.ConstantAttribute;
import sg.edu.nus.iss.t4p.phoenix.core.exceptions.BusinessLogicException;
import sg.edu.nus.iss.t4p.phoenix.delegate.security.AuthenticateDelegate;
import sg.edu.nus.iss.t4p.phoenix.entity.user.User;
import sg.edu.nus.iss.t4p.phoenix.utility.T4StringUtil;

/**
* Login Authentication Controller Action class 
* @author Mansoor M I A0092661A
* @version 1.0
*/
@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/authenticateController/*")
public class AuthenticateController extends BaseController {
	
	/**
	 * Method to authenticate users by sending in User class. 
	 * Authenticated users will be redirected to the requested page.
	 * Method will throw exception if either User Name or Password left empty.
	 * @param request the HttpServletRequest
	 * @param response the HttpServletResponse
	 * @exception javax.servlet.ServletException
	 * @exception java.io.IOException
	 * @see javax.servlet.ServletException
	 * @see java.io.IOException
	 */
	protected void doLogin(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{
		User user = super.retrieveParameter(request,User.class);
		try{
			if(T4StringUtil.isEmpty(user.getUsername()) || T4StringUtil.isEmpty(user.getPassword()))
				throw new BusinessLogicException("Please fill in Username and Password");
			
			user = (new AuthenticateDelegate()).authenticateUser(user);
			if(user!= null)
				request.getSession().setAttribute(ConstantAttribute.USER_SESSION, user);
		
			super.doRedirect(request, response, "/");
		}catch(Exception e){
			
			request.setAttribute("ERR", e.getMessage());
			request.getRequestDispatcher("/pages/login/login.jsp").forward(request, response);
		}
	}
	
	/**
	 * Method to perform logout for authenticated users.
	 * @param request the HttpServletRequest
	 * @param response the HttpServletResponse
	 * @exception javax.servlet.ServletException
	 * @exception java.io.IOException
	 * @see javax.servlet.ServletException
	 * @see java.io.IOException
	 */	
	protected void doLogout(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{
		request.getSession().invalidate();
		request.getRequestDispatcher("/").forward(request, response);
	}
	
	

}
