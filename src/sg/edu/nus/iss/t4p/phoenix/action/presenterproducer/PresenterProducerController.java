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
 * 20 Sep 2012    	Team 4		    0.1				Initial creating
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

import sg.edu.nus.iss.t4p.phoenix.entity.user.User;
import sg.edu.nus.iss.t4p.phoenix.core.action.BaseController;
import sg.edu.nus.iss.t4p.phoenix.core.constant.ConstantAttribute;
import sg.edu.nus.iss.t4p.phoenix.delegate.user.*;
import sg.edu.nus.iss.t4p.phoenix.utility.T4StringUtil;

/**
 * 
 * Servlet Implementation class for PresenterProducer Controller
 *
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/presenterproducerController/*")
public class PresenterProducerController {
	
	/**
	 * Method for listing PresenterProducer
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doList(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{

		List<User> presenterproducer = UserDelegate.getInstance().retrievePresenterProducerList(true);
		
		String callBackUrl = (String) request.getAttribute(ConstantAttribute.CALL_BACK_URL);
		if(T4StringUtil.isEmpty(callBackUrl))
			callBackUrl = request.getParameter(ConstantAttribute.CALL_BACK_URL);
			
		request.setAttribute(ConstantAttribute.CALL_BACK_URL, callBackUrl);
		
		request.setAttribute("presenterproducer", presenterproducer);
		request.getRequestDispatcher("/pages/presenterproducer/list_presenterproducer.jsp").forward(request, response);		
	
		System.out.print("PresenterProducer DoList");
	}
	

}
