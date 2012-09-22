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
package sg.edu.nus.iss.t4p.phoenix.action.presenterproducer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.edu.nus.iss.t4p.phoenix.core.action.BaseController;
import sg.edu.nus.iss.t4p.phoenix.core.constant.ConstantAttribute;
import sg.edu.nus.iss.t4p.phoenix.delegate.user.UserDelegate;
import sg.edu.nus.iss.t4p.phoenix.entity.user.User;
import sg.edu.nus.iss.t4p.phoenix.utility.T4StringUtil;

/**
* Presenter Producer Controller Action class 
* @author Robin Foe A0092657U
* @version 1.0
*/
@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/PresenterProducerController/*")
public class PresenterProducerController extends BaseController {
	
	/**
	 * Method to retrieve the list of presenters and forward the request to list_presenter page
	 * @param request the HttpServletRequest
	 * @param response the HttpServletResponse
	 * @exception javax.servlet.ServletException
	 * @exception java.io.IOException
	 * @see javax.servlet.ServletException
	 * @see java.io.IOException
	 */
	protected void doPresenterList(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{

		List<User> presenters = UserDelegate.getInstance().retrievePresenterProducerList(true);
		String callBackUrl = (String) request.getAttribute(ConstantAttribute.CALL_BACK_URL);
		if(T4StringUtil.isEmpty(callBackUrl))
			callBackUrl = request.getParameter(ConstantAttribute.CALL_BACK_URL);
			
		request.setAttribute(ConstantAttribute.CALL_BACK_URL, callBackUrl);
		request.setAttribute("presenters", presenters);
		request.getRequestDispatcher("/pages/presenter/list_presenter.jsp").forward(request, response);		
	}
	
	/**
	 * Method to retrieve a particular presenter based on User class passed in as parameter in http request.
	 * @param request the HttpServletRequest
	 * @param response the HttpServletResponse
	 * @exception javax.servlet.ServletException
	 * @exception java.io.IOException
	 * @see javax.servlet.ServletException
	 * @see java.io.IOException
	 */
	protected void doSelectPresenter(HttpServletRequest request,	HttpServletResponse response)throws ServletException, IOException{
		User presenter = super.retrieveParameter(request, User.class);
		String callBackUrl = request.getParameter(ConstantAttribute.CALL_BACK_URL);
		request.setAttribute(ConstantAttribute.SELECTED_ITEM, presenter);
		request.getRequestDispatcher(callBackUrl).forward(request, response);	
	}
	
	/**
	 * Method to retrieve the list of producers and forward the request to list_producer page
	 * @param request the HttpServletRequest
	 * @param response the HttpServletResponse
	 * @exception javax.servlet.ServletException
	 * @exception java.io.IOException
	 * @see javax.servlet.ServletException
	 * @see java.io.IOException
	 */	
	protected void doProducerList(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{

		List<User> producers = UserDelegate.getInstance().retrievePresenterProducerList(false);
		String callBackUrl = (String) request.getAttribute(ConstantAttribute.CALL_BACK_URL);
		if(T4StringUtil.isEmpty(callBackUrl))
			callBackUrl = request.getParameter(ConstantAttribute.CALL_BACK_URL);
			
		request.setAttribute(ConstantAttribute.CALL_BACK_URL, callBackUrl);
		request.setAttribute("producers", producers);
		request.getRequestDispatcher("/pages/producer/list_producer.jsp").forward(request, response);		
	}
	
	/**
	 * Method to retrieve a particular producer based on User class passed in as parameter in http request.
	 * @param request the HttpServletRequest
	 * @param response the HttpServletResponse
	 * @exception javax.servlet.ServletException
	 * @exception java.io.IOException
	 * @see javax.servlet.ServletException
	 * @see java.io.IOException
	 */	
	protected void doSelectProducer(HttpServletRequest request,	HttpServletResponse response)throws ServletException, IOException{
		User producer = super.retrieveParameter(request, User.class);
		String callBackUrl = request.getParameter(ConstantAttribute.CALL_BACK_URL);
		request.setAttribute(ConstantAttribute.SELECTED_ITEM, producer);
		request.getRequestDispatcher(callBackUrl).forward(request, response);	
	}
}
