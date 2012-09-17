package sg.edu.nus.iss.t4p.phoenix.service.schedule;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import sg.edu.nus.iss.t4p.phoenix.core.dao.DaoFactory;
import sg.edu.nus.iss.t4p.phoenix.core.exceptions.BusinessLogicException;
import sg.edu.nus.iss.t4p.phoenix.entity.scalar.MonthlySchedule;
import sg.edu.nus.iss.t4p.phoenix.entity.scalar.WeeklySchedule;
import sg.edu.nus.iss.t4p.phoenix.entity.schedule.ProgramSlot;
import sg.edu.nus.iss.t4p.phoenix.utility.T4DateUtil;

public class ScheduleService {
	
	public static ScheduleService instance;
	private ScheduleService(){/*SINGELTON*/}
	public static ScheduleService getInstance(){
		if(instance == null)
			instance = new ScheduleService();
		
		return instance;
	}
	
	public MonthlySchedule getMonthlySlotByDate(Date date) throws BusinessLogicException{
		Calendar cal = T4DateUtil.getCalendar(date);
		MonthlySchedule monthlySchedule = new MonthlySchedule();
		monthlySchedule.setMonth(cal.get(Calendar.MONTH));
		monthlySchedule.setYear(cal.get(Calendar.YEAR));
		monthlySchedule.setMonthAsText(new SimpleDateFormat("MMMM").format(cal.getTime()));

		try{
			cal.set(Calendar.DATE,1);
			while(monthlySchedule.getMonth() == cal.get(Calendar.MONTH)){
				cal = T4DateUtil.rollToFirstDayOfWeek(cal);
				
				WeeklySchedule weeklySchedule = new WeeklySchedule();
				weeklySchedule.setStartDate(cal.getTime());
				weeklySchedule.setWeekNumber(cal.get(Calendar.WEEK_OF_YEAR));
				monthlySchedule.getWeekSchedules().add(weeklySchedule);
				
				
				int[] programCount = DaoFactory.getInstance().getProgramSlotDao().getMonthlyProgramCountByWeek(cal.getTime());
				weeklySchedule.setProgramCount(programCount);
				
				cal.add(Calendar.DATE, 7);
			}
			
		}catch(Exception e){
			throw new BusinessLogicException(e.getMessage());
		}
		return monthlySchedule;
	}
	
	
	public WeeklySchedule getWeeklySlotByDate(Date date)throws BusinessLogicException{
		Calendar calFrom = T4DateUtil.getCalendar(date);
		calFrom = T4DateUtil.rollToFirstDayOfWeek(calFrom);
		
		Calendar calTo = T4DateUtil.getOneWeekLater(calFrom.getTime());
		
		WeeklySchedule weeklySchedule = new WeeklySchedule();
		weeklySchedule.setStartDate(calFrom.getTime());
		weeklySchedule.setWeekNumber(calFrom.get(Calendar.WEEK_OF_YEAR));
		
		try {
			List<ProgramSlot> programSlots =  DaoFactory.getInstance().getProgramSlotDao().getProgramSlotByDateRange(calFrom.getTime(), calTo.getTime());
			weeklySchedule.setProgramSlots(programSlots);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return weeklySchedule;
	}
	
	public void saveWeeklySlot(WeeklySchedule weeklySchedule) throws BusinessLogicException{
		
	}
	
	protected void validateSlot(WeeklySchedule weeklySchedule)throws BusinessLogicException{
		
	}
	

}
