package sg.edu.nus.iss.t4p.phoenix.delegate.schedule;

import java.util.Date;

import sg.edu.nus.iss.t4p.phoenix.core.exceptions.BusinessLogicException;
import sg.edu.nus.iss.t4p.phoenix.entity.scalar.MonthlySchedule;
import sg.edu.nus.iss.t4p.phoenix.entity.scalar.WeeklySchedule;
import sg.edu.nus.iss.t4p.phoenix.service.schedule.ScheduleService;

/**
* Delegation class for Scheduler
* @author Robin Foe A0092657U
* @version 1.0
*/
public class ScheduleDelegate {
	public static ScheduleDelegate instance;
	private ScheduleDelegate(){/*SINGLETON*/}
	public static ScheduleDelegate getInstance(){
		if(instance == null)
			instance = new ScheduleDelegate();
		
		return instance;
	}

	
	/**
	 * This method perform the delegation to ScheduleService.getMonthlySlotByDate
	 * @param date the java.util.Date of the month
	 * @return sg.edu.nus.iss.t4p.phoenix.entity.scalar.MonthlySchedule
	 * @exception sg.edu.nus.iss.t4p.phoenix.core.exceptions.BusinessLogicException
	 */
	public MonthlySchedule getMonthlySlotByDate(Date date) throws BusinessLogicException{
		return ScheduleService.getInstance().getMonthlySlotByDate(date);
	}
	
	
	/**
	 * This method perform the delegation to ScheduleService.getWeeklySlot
	 * @param date the java.util.Date of the month
	 * @return sg.edu.nus.iss.t4p.phoenix.entity.scalar.WeeklySchedule
	 * @exception sg.edu.nus.iss.t4p.phoenix.core.exceptions.BusinessLogicException
	 */
	public WeeklySchedule getWeeklySlotByDate(Date date) throws BusinessLogicException{
		return ScheduleService.getInstance().getWeeklySlotByDate(date);
	}

	
	/**
	 * This method perform the delegation to ScheduleService.saveWeeklySlot
	 * @param weeklySchedule sg.edu.nus.iss.t4p.phoenix.entity.scalar.WeeklySchedule
	 * @exception sg.edu.nus.iss.t4p.phoenix.core.exceptions.BusinessLogicException
	 */
	public void saveWeeklySlot(WeeklySchedule weeklySchedule) throws BusinessLogicException{
		ScheduleService.getInstance().saveWeeklySlot(weeklySchedule);
	}
	
}
