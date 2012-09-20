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
 * 15 Sep 2012    	Team 4		    1.0				Initial creating
 * 													
 * 													
 * 
 */
package sg.edu.nus.iss.t4p.phoenix.action.radioprogram;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.edu.nus.iss.t4p.phoenix.core.action.BaseController;
import sg.edu.nus.iss.t4p.phoenix.core.constant.ConstantAttribute;
import sg.edu.nus.iss.t4p.phoenix.delegate.radioprogram.RadioProgramDelegate;
import sg.edu.nus.iss.t4p.phoenix.entity.radioprogram.RadioProgram;
import sg.edu.nus.iss.t4p.phoenix.utility.T4StringUtil;

/**
 * Servlet implementation class for RadioProgramController
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/radioprogramController/*")
public class RadioProgramController extends BaseController {
	
	/**
	 * List radio programs
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doList(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{

		List<RadioProgram> radioprograms = RadioProgramDelegate.getInstance().retrieveProgramList();
		String callBackUrl = (String) request.getAttribute(ConstantAttribute.CALL_BACK_URL);
		if(T4StringUtil.isEmpty(callBackUrl))
			callBackUrl = request.getParameter(ConstantAttribute.CALL_BACK_URL);
			
		request.setAttribute(ConstantAttribute.CALL_BACK_URL, callBackUrl);
		
		request.setAttribute("radioprograms", radioprograms);
		request.getRequestDispatcher("/pages/radioprogram/list_radioprogram.jsp").forward(request, response);		
	}
	
	
	/**
	 * Select radio program
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doSelectRadioProgram(HttpServletRequest request,	HttpServletResponse response)throws ServletException, IOException{
		RadioProgram radioProgram = super.retrieveParameter(request, RadioProgram.class);
		String callBackUrl = request.getParameter(ConstantAttribute.CALL_BACK_URL);
		request.setAttribute(ConstantAttribute.SELECTED_ITEM, radioProgram);
		request.getRequestDispatcher(callBackUrl).forward(request, response);	
	}
	/*
	protected void doDisplay(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{
        RadioProgram program = (RadioProgramDelegate.getInstance()).getByProgramName(request.getParameter("programName"));
		request.setAttribute("programList",program);
		request.getRequestDispatcher("/pages/radioprogram/view_radioprogram.jsp").forward(request, response);		
	}
	*/
}
