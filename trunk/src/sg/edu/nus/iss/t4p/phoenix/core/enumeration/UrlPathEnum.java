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
 * 15 Sep 2012    	Team 4		    0.1				Initial creating
 * 													
 * 													
 * 
 */
package sg.edu.nus.iss.t4p.phoenix.core.enumeration;

/**
* Enumeration for PRMS URL
* @author Robin Foe A0092657U
* @version 1.0
*/

public enum UrlPathEnum {
	ACTION_LOGIN("/authenticate.do", "/controller/authenticate.do" ,"/authenticateController/login.do"),
	ACTION_LOGOUT("/logout.do", "/controller/logout.do" ,"/authenticateController/logout.do"),
	
	ACTION_LIST_SCHEDULE("/listSchedule.do", "/controller/listSchedule.do" ,"/scheduleController/list.do"),
	ACTION_MAINTAIN_SCHEDULE("/maintainSchedule.do", "/controller/maintainSchedule.do" ,"/scheduleController/maintain.do"),
	ACTION_SAVE_SCHEDULE("/saveSchedule.do", "/controller/saveSchedule.do" ,"/scheduleController/save.do"),
	
	ACTION_PICK_RADIO_SCHEDULE("/pickRadioProgram.do", "/controller/pickRadioProgram.do" ,"/scheduleController/pickRadioProgram.do"),
	ACTION_PICK_PRESENTER_SCHEDULE("/pickPresenter.do", "/controller/pickPresenter.do" ,"/scheduleController/pickPresenter.do"),
	ACTION_PICK_PRODUCER_SCHEDULE("/pickProducer.do", "/controller/pickProducer.do" ,"/scheduleController/pickProducer.do"),
	ACTION_PICK_SCHEDULED_PROGRAM("/pickScheduledProgram.do", "/controller/pickScheduledProgram.do" ,"/scheduleController/pickScheduledProgram.do"),
	
	ACTION_CALLBACK_RADIO_SCHEDULE("/callBackRadioProgram.do", "/controller/callBackRadioProgram.do" ,"/scheduleController/callBackRadioProgram.do"),
	ACTION_CALLBACK_PRESENTER_SCHEDULE("/callBackPresenter.do", "/controller/callBackPresenter.do" ,"/scheduleController/callBackPresenter.do"),
	ACTION_CALLBACK_PRODUCER_SCHEDULE("/callBackProducer.do", "/controller/callBackProducer.do" ,"/scheduleController/callBackProducer.do"),
	ACTION_CALLBACK_SCHEDULED_PROGRAM("/callBackScheduledProgram.do", "/controller/callBackScheduledProgram.do" ,"/scheduleController/callBackScheduledProgram.do"),
	
	ACTION_PICK_RADIO_LIST("/pickRadioList.do", "/controller/pickRadioList.do" ,"/radioprogramController/list.do"),
	ACTION_SELECT_RADIO_LIST("/selectRadioProgram.do", "/controller/selectRadioProgram.do" ,"/radioprogramController/selectRadioProgram.do"),
	
	ACTION_PICK_SCHEDULED_LIST("/pickScheduledProgramList.do", "/controller/pickScheduledProgramList.do" ,"/scheduledProgramController/list.do"),
	ACTION_SELECT_SCHEDULED_LIST("/selectScheduledProgram.do", "/controller/selectScheduledProgram.do" ,"/scheduledProgramController/selectScheduledProgram.do"),
	
	ACTION_LIST_USER("/listUser.do", "/controller/listUser.do" ,"/userController/list.do"),
	ACTION_INIT_USER("/initUser.do", "/controller/initUser.do" ,"/userController/init.do"),
	ACTION_SAVE_USER("/saveUser.do", "/controller/saveUser.do" ,"/userController/save.do"),
	
	ACTION_PICK_PRESENTER_LIST("/pickPresenterList.do", "/controller/pickPresenterList.do" ,"/PresenterProducerController/PresenterList.do"),
	ACTION_SELECT_PRESENTER_LIST("/selectPresenter.do", "/controller/selectPresenter.do" ,"/PresenterProducerController/selectPresenter.do"),
	
	ACTION_PICK_PRODUCER_LIST("/pickProducerList.do", "/controller/pickProducerList.do" ,"/PresenterProducerController/ProducerList.do"),
	ACTION_SELECT_PRODUCER_LIST("/selectProducer.do", "/controller/selectProducer.do" ,"/PresenterProducerController/selectProducer.do"),
	
	ACTION_LIST_RADIOPROGRAM("/listRadioProgram.do", "/controller/listRadioProgram.do" ,"/radioprogramController/list.do"),
	
	PAGE_HOME("/", "/" ,"/");
	
	private String controlPath;
	public String getControlPath() {return controlPath;}
	
	private String frontControlPath;
	public String getFrontControlPath() {return frontControlPath;}
	
	private String destinationPath;
	public String getDestinationPath() {return destinationPath;}

	UrlPathEnum(String controlPath , String frontControlPath, String destinationPath){
		this.controlPath = controlPath;
		this.frontControlPath = frontControlPath;
		this.destinationPath = destinationPath;
	}
	
	public static UrlPathEnum getByControlPath(String path){
		for(UrlPathEnum urlPath : UrlPathEnum.values()){
			if(urlPath.getControlPath().equals(path))
				return urlPath;
		}
		
		return PAGE_HOME;
	}
	
}
