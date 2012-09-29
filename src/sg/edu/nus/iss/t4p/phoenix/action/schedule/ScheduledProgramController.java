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
package sg.edu.nus.iss.t4p.phoenix.action.schedule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.edu.nus.iss.t4p.phoenix.core.action.BaseController;
import sg.edu.nus.iss.t4p.phoenix.core.constant.ConstantAttribute;
import sg.edu.nus.iss.t4p.phoenix.core.exceptions.BusinessLogicException;
import sg.edu.nus.iss.t4p.phoenix.delegate.schedule.ScheduleDelegate;
import sg.edu.nus.iss.t4p.phoenix.entity.scalar.WeeklySchedule;
import sg.edu.nus.iss.t4p.phoenix.entity.schedule.ProgramSlot;
import sg.edu.nus.iss.t4p.phoenix.utility.T4DateUtil;
import sg.edu.nus.iss.t4p.phoenix.utility.T4StringUtil;

/**
* Schedule Program Controller Action class 
* @author Robin Foe A0092657U , Kenny Hartono A0092674W
* @version 1.0
*/
@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/scheduledProgramController/*")
public class ScheduledProgramController extends BaseController{
	
	/**
	 * Method to list scheduled program by week
	 * @param request the HttpServletRequest
	 * @param response the HttpServletResponse
	 * @exception javax.servlet.ServletException
	 * @exception java.io.IOException
	 * @see javax.servlet.ServletException
	 * @see java.io.IOException
	 */
	protected void doList(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{
		WeeklySchedule weeklySchedule = super.retrieveParameter(request, WeeklySchedule.class);
		String callBackUrl = request.getParameter(ConstantAttribute.CALL_BACK_URL);
		Calendar cal = null;
		
		if(T4StringUtil.isEmpty(callBackUrl)){
			// this is initial call, need to get the call back url and the rest
			callBackUrl = (String) request.getAttribute(ConstantAttribute.CALL_BACK_URL);
			cal = T4DateUtil.getCalendar(new Date());
			weeklySchedule.setStartDate(cal.getTime());
		}else
			cal = T4DateUtil.getCalendar(weeklySchedule.getStartDate());

		try {
			weeklySchedule = ScheduleDelegate.getInstance().getWeeklySlotByDate(cal.getTime());
		} catch (BusinessLogicException e) {
			e.printStackTrace();
		}
		
		request.setAttribute(ConstantAttribute.CALL_BACK_URL,callBackUrl);
		request.setAttribute("weeklySchedule",weeklySchedule);
		request.getRequestDispatcher("/pages/schedule/list_scheduled_program.jsp").forward(request, response);
	}
	
	/**
	 * Method to select scheduled program by week and then to loop through slots in the week.
	 * @param request the HttpServletRequest
	 * @param response the HttpServletResponse
	 * @exception javax.servlet.ServletException
	 * @exception java.io.IOException
	 * @see javax.servlet.ServletException
	 * @see java.io.IOException
	 */	
	protected void doSelectScheduledProgram(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{
		WeeklySchedule weeklySchedule = super.retrieveParameter(request, WeeklySchedule.class);
		List<ProgramSlot> programSlots = new ArrayList<ProgramSlot>();
		
		for(ProgramSlot selectedSlot : weeklySchedule.getProgramSlots()){
			if(selectedSlot.isSelected())
				programSlots.add(selectedSlot);
		}
		
		
		request.setAttribute(ConstantAttribute.SELECTED_ITEM, programSlots);
		request.getRequestDispatcher( request.getParameter(ConstantAttribute.CALL_BACK_URL) ).forward(request, response);	
	}
	

}
