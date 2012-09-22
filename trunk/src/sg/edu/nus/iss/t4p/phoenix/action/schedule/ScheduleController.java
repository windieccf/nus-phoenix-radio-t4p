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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.edu.nus.iss.t4p.phoenix.core.action.BaseController;
import sg.edu.nus.iss.t4p.phoenix.core.constant.ConstantAttribute;
import sg.edu.nus.iss.t4p.phoenix.core.enumeration.UrlPathEnum;
import sg.edu.nus.iss.t4p.phoenix.core.exceptions.BusinessLogicException;
import sg.edu.nus.iss.t4p.phoenix.delegate.schedule.ScheduleDelegate;
import sg.edu.nus.iss.t4p.phoenix.entity.radioprogram.RadioProgram;
import sg.edu.nus.iss.t4p.phoenix.entity.scalar.MonthlySchedule;
import sg.edu.nus.iss.t4p.phoenix.entity.scalar.WeeklySchedule;
import sg.edu.nus.iss.t4p.phoenix.entity.schedule.ProgramSlot;
import sg.edu.nus.iss.t4p.phoenix.entity.user.User;
import sg.edu.nus.iss.t4p.phoenix.utility.T4DateUtil;

/**
* Schedule Controller Action class 
* @author Robin Foe A0092657U
* @version 1.0
*/
@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/scheduleController/*")
public class ScheduleController extends BaseController {

	
	/**
	 * This method perform the listing logic for Calendar View
	 * @param request the HttpServletRequest
	 * @param response the HttpServletResponse
	 * @exception javax.servlet.ServletException
	 * @exception java.io.IOException
	 * @see javax.servlet.ServletException
	 * @see java.io.IOException
	 */
	protected void doList(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{
		MonthlySchedule monthlySchedule = super.retrieveParameter(request, MonthlySchedule.class);
		if(monthlySchedule.isMonthlyView())
			this.doPrepareMonthlyView(request, response, monthlySchedule);
		
		request.getRequestDispatcher("/pages/schedule/list_schedule.jsp").forward(request, response);
	}
	
	/**
	 * This method perform bring up the Weekly Program slot data to the user
	 * @param request the HttpServletRequest
	 * @param response the HttpServletResponse
	 * @exception javax.servlet.ServletException
	 * @exception java.io.IOException
	 * @see javax.servlet.ServletException
	 * @see java.io.IOException
	 */
	protected void doMaintain(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{
		
		String yearAsString = request.getParameter("monthlySchedule.year");
		String weekNumberAsString = request.getParameter("weeklySchedule.weekNumber");
		
		Calendar cal = T4DateUtil.getCalendar(new Date());
		cal.set(Calendar.YEAR, Integer.parseInt(yearAsString));
		cal.set(Calendar.WEEK_OF_YEAR, Integer.parseInt(weekNumberAsString));
		
		WeeklySchedule weeklySchedule = null;
		try {
			weeklySchedule = ScheduleDelegate.getInstance().getWeeklySlotByDate(cal.getTime());
			request.setAttribute("weeklySchedule", weeklySchedule);
		} catch (BusinessLogicException e) {
			e.printStackTrace();
			request.setAttribute(ConstantAttribute.MESSAGE_ERR, e.getMessage());
		}
		
		request.getRequestDispatcher("/pages/schedule/maintain_schedule.jsp").forward(request, response);
	}
	
	/**
	 * This method perform save  / update of the user entry
	 * @param request the HttpServletRequest
	 * @param response the HttpServletResponse
	 * @exception javax.servlet.ServletException
	 * @exception java.io.IOException
	 * @see javax.servlet.ServletException
	 * @see java.io.IOException
	 */
	protected void doSave(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{
		WeeklySchedule weeklySchedule = super.retrieveParameter(request, WeeklySchedule.class);
		try {
			ScheduleDelegate.getInstance().saveWeeklySlot(weeklySchedule);
		} catch (BusinessLogicException e) {
			e.printStackTrace();
			request.setAttribute(ConstantAttribute.MESSAGE_ERR, e.getMessage());
			request.setAttribute("weeklySchedule", weeklySchedule);
			request.getRequestDispatcher("/pages/schedule/maintain_schedule.jsp").forward(request, response);
		}
		request.setAttribute(ConstantAttribute.MESSAGE_INFO, "Successfully Save");
		request.getRequestDispatcher(UrlPathEnum.ACTION_LIST_SCHEDULE.getFrontControlPath()).forward(request, response);
		//this.doList(request, response);
	}
	
	/**
	 * Method to prepare for viewing schedule by year month day
	 * @param request the HttpServletRequest
	 * @param response the HttpServletResponse
	 * @exception javax.servlet.ServletException
	 * @exception java.io.IOException
	 * @see javax.servlet.ServletException
	 * @see java.io.IOException
	 */
	private void doPrepareMonthlyView(HttpServletRequest request,	HttpServletResponse response , MonthlySchedule monthlySchedule) throws ServletException, IOException{
		
		Calendar cal = new GregorianCalendar();
		if(monthlySchedule.getMonth() == -2){
			monthlySchedule.setMonth(cal.get(Calendar.MONTH));
			monthlySchedule.setYear(cal.get(Calendar.YEAR));
		}else{
			cal.set(Calendar.YEAR, monthlySchedule.getYear());
			if(monthlySchedule.getMonth() == -1){
				cal.add(Calendar.YEAR, -1);
				cal.set(Calendar.MONTH, 11);
			}else if(monthlySchedule.getMonth() == 12){
				cal.add(Calendar.YEAR, 1);
				cal.set(Calendar.MONTH, 0);
			}else{
				cal.set(Calendar.MONTH, monthlySchedule.getMonth());
			}
		}

		try {
			monthlySchedule = ScheduleDelegate.getInstance().getMonthlySlotByDate(cal.getTime());
		} catch (BusinessLogicException e) {
			e.printStackTrace();
			request.setAttribute(ConstantAttribute.MESSAGE_ERR, e.getMessage());
		}
		request.setAttribute("monthlySchedule", monthlySchedule);
	}
	
	/** PICK AND CALL BACK RELATED **********************************************************/ 
	protected void doPickRadioProgram(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{
		WeeklySchedule weeklySchedule = super.retrieveParameter(request, WeeklySchedule.class);
		request.getSession().setAttribute(ConstantAttribute.FLASH_SESSION, weeklySchedule);
		request.setAttribute(ConstantAttribute.CALL_BACK_URL, UrlPathEnum.ACTION_CALLBACK_RADIO_SCHEDULE.getFrontControlPath());
		request.getRequestDispatcher(UrlPathEnum.ACTION_PICK_RADIO_LIST.getFrontControlPath()).forward(request, response);
	}
	
	/**
	 * Callback method for RadioProgram
	 * @param request the HttpServletRequest
	 * @param response the HttpServletResponse
	 * @exception javax.servlet.ServletException
	 * @exception java.io.IOException
	 * @see javax.servlet.ServletException
	 * @see java.io.IOException
	 */
	protected void doCallBackRadioProgram(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{
		RadioProgram radioProgram = (RadioProgram)request.getAttribute(ConstantAttribute.SELECTED_ITEM);
		WeeklySchedule weeklySchedule = (WeeklySchedule) request.getSession().getAttribute(ConstantAttribute.FLASH_SESSION);
		request.getSession().removeAttribute(ConstantAttribute.FLASH_SESSION);
		
		for(ProgramSlot programSlot : weeklySchedule.getProgramSlots()){
			if(programSlot.isSelected()){
				programSlot.setRadioProgram(radioProgram);
				programSlot.setRadioProgramId(radioProgram.getId());
				programSlot.toggleSelected();
			}
		}
		request.setAttribute("weeklySchedule", weeklySchedule);
		request.getRequestDispatcher("/pages/schedule/maintain_schedule.jsp").forward(request, response);
		
	}
	
	/**
	 * Method for selecting Scheduled Program by Week. The weekly information retrieved from Http Request variable
	 * @param request the HttpServletRequest
	 * @param response the HttpServletResponse
	 * @exception javax.servlet.ServletException
	 * @exception java.io.IOException
	 * @see javax.servlet.ServletException
	 * @see java.io.IOException
	 */
	protected void doPickScheduledProgram(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{
		WeeklySchedule weeklySchedule = super.retrieveParameter(request, WeeklySchedule.class);
		request.getSession().setAttribute(ConstantAttribute.FLASH_SESSION, weeklySchedule);
		request.setAttribute(ConstantAttribute.CALL_BACK_URL, UrlPathEnum.ACTION_CALLBACK_SCHEDULED_PROGRAM.getFrontControlPath());
		request.getRequestDispatcher(UrlPathEnum.ACTION_PICK_SCHEDULED_LIST.getFrontControlPath()).forward(request, response);

	}
	
	/**
	 * Method for ScheduledProgram CallBack. 
	 * The weekly information retrieved from Http Request variable and others retrieved from constant variables.
	 * Method to loop through all the slots in a selected program.
	 * @param request the HttpServletRequest
	 * @param response the HttpServletResponse
	 * @exception javax.servlet.ServletException
	 * @exception java.io.IOException
	 * @see javax.servlet.ServletException
	 * @see java.io.IOException
	 */
	@SuppressWarnings("unchecked")
	protected void doCallBackScheduledProgram(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{

		List<ProgramSlot> selectedSlots = (List<ProgramSlot>)request.getAttribute(ConstantAttribute.SELECTED_ITEM);
		WeeklySchedule weeklySchedule = (WeeklySchedule) request.getSession().getAttribute(ConstantAttribute.FLASH_SESSION);
		request.getSession().removeAttribute(ConstantAttribute.FLASH_SESSION);
		
		for(ProgramSlot selectedSlot : selectedSlots){
			Calendar startDate = T4DateUtil.alignDateTimeByDayOfWeek(weeklySchedule.getStartDate() , selectedSlot.getStartDateTime());
			Calendar endDate = T4DateUtil.alignDateTimeByDayOfWeek(weeklySchedule.getStartDate() , selectedSlot.getEndDateTime());
		
			selectedSlot.setStartDateTime(startDate.getTime());
			selectedSlot.setEndDateTime(endDate.getTime());
			selectedSlot.toggleSelected();
			
			weeklySchedule.getProgramSlots().add(selectedSlot);
		}

		request.setAttribute("weeklySchedule", weeklySchedule);
		request.getRequestDispatcher("/pages/schedule/maintain_schedule.jsp").forward(request, response);
		
	}
	
	/**
	 * Method for selecting Presenter
	 * @param request the HttpServletRequest
	 * @param response the HttpServletResponse
	 * @exception javax.servlet.ServletException
	 * @exception java.io.IOException
	 * @see javax.servlet.ServletException
	 * @see java.io.IOException
	 */
	protected void doPickPresenter(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{
		WeeklySchedule weeklySchedule = super.retrieveParameter(request, WeeklySchedule.class);
		request.getSession().setAttribute(ConstantAttribute.FLASH_SESSION, weeklySchedule);
		request.setAttribute(ConstantAttribute.CALL_BACK_URL, UrlPathEnum.ACTION_CALLBACK_PRESENTER_SCHEDULE.getFrontControlPath());
		request.getRequestDispatcher(UrlPathEnum.ACTION_PICK_PRESENTER_LIST.getFrontControlPath()).forward(request, response);	
	}
	
	/**
	 * Method for CallBack - Presenter Information
	 * @param request the HttpServletRequest
	 * @param response the HttpServletResponse
	 * @exception javax.servlet.ServletException
	 * @exception java.io.IOException
	 * @see javax.servlet.ServletException
	 * @see java.io.IOException
	 */	
	protected void doCallBackPresenter(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{
		User presenter = (User)request.getAttribute(ConstantAttribute.SELECTED_ITEM);
		WeeklySchedule weeklySchedule = (WeeklySchedule) request.getSession().getAttribute(ConstantAttribute.FLASH_SESSION);
		request.getSession().removeAttribute(ConstantAttribute.FLASH_SESSION);
		
		for(ProgramSlot programSlot : weeklySchedule.getProgramSlots()){
			if(programSlot.isSelected()){
				programSlot.setPresenter(presenter);
				programSlot.setPresenterId(presenter.getId());
				programSlot.toggleSelected();
			}
		}
		request.setAttribute("weeklySchedule", weeklySchedule);
		request.getRequestDispatcher("/pages/schedule/maintain_schedule.jsp").forward(request, response);	
	}
	
	/**
	 * Method for selecting Producer
	 * @param request the HttpServletRequest
	 * @param response the HttpServletResponse
	 * @exception javax.servlet.ServletException
	 * @exception java.io.IOException
	 * @see javax.servlet.ServletException
	 * @see java.io.IOException
	 */	
	protected void doPickProducer(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{
		WeeklySchedule weeklySchedule = super.retrieveParameter(request, WeeklySchedule.class);
		request.getSession().setAttribute(ConstantAttribute.FLASH_SESSION, weeklySchedule);
		request.setAttribute(ConstantAttribute.CALL_BACK_URL, UrlPathEnum.ACTION_CALLBACK_PRODUCER_SCHEDULE.getFrontControlPath());
		request.getRequestDispatcher(UrlPathEnum.ACTION_PICK_PRODUCER_LIST.getFrontControlPath()).forward(request, response);	
	
	}
	
	/**
	 * Method for CallBack - Producer Information
	 * @param request the HttpServletRequest
	 * @param response the HttpServletResponse
	 * @exception javax.servlet.ServletException
	 * @exception java.io.IOException
	 * @see javax.servlet.ServletException
	 * @see java.io.IOException
	 */		
	protected void doCallBackProducer(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{
		User producer = (User)request.getAttribute(ConstantAttribute.SELECTED_ITEM);
		WeeklySchedule weeklySchedule = (WeeklySchedule) request.getSession().getAttribute(ConstantAttribute.FLASH_SESSION);
		request.getSession().removeAttribute(ConstantAttribute.FLASH_SESSION);
		
		for(ProgramSlot programSlot : weeklySchedule.getProgramSlots()){
			if(programSlot.isSelected()){
				programSlot.setProducer(producer);
				programSlot.setProducerId(producer.getId());
				programSlot.toggleSelected();
			}
		}
		request.setAttribute("weeklySchedule", weeklySchedule);
		request.getRequestDispatcher("/pages/schedule/maintain_schedule.jsp").forward(request, response);		
	}
	
	
}
