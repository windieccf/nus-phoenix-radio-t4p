package sg.edu.nus.iss.t4p.phoenix.delegate.schedule;

import java.util.Date;

import sg.edu.nus.iss.t4p.phoenix.core.exceptions.BusinessLogicException;
import sg.edu.nus.iss.t4p.phoenix.entity.scalar.MonthlySchedule;
import sg.edu.nus.iss.t4p.phoenix.entity.scalar.WeeklySchedule;
import sg.edu.nus.iss.t4p.phoenix.service.schedule.ScheduleService;

public class ScheduleDelegate {
	public static ScheduleDelegate instance;
	private ScheduleDelegate(){/*SINGLETON*/}
	public static ScheduleDelegate getInstance(){
		if(instance == null)
			instance = new ScheduleDelegate();
		
		return instance;
	}

	public MonthlySchedule getMonthlySlotByDate(Date date) throws BusinessLogicException{
		return ScheduleService.getInstance().getMonthlySlotByDate(date);
	}
	
	public WeeklySchedule getWeeklySlotByDate(Date date) throws BusinessLogicException{
		return ScheduleService.getInstance().getWeeklySlotByDate(date);
	}

	public void saveWeeklySlot(WeeklySchedule weeklySchedule) throws BusinessLogicException{
		
	}
	
}
