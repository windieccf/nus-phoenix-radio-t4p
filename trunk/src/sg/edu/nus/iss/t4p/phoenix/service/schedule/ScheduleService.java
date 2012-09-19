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

/**
* Service class for Scheduler
* @author Robin Foe A0092657U
* @version 1.0
*/
public class ScheduleService {
	
	public static ScheduleService instance;
	private ScheduleService(){/*SINGELTON*/}
	public static ScheduleService getInstance(){
		if(instance == null)
			instance = new ScheduleService();
		
		return instance;
	}
	
	
	/**
	 * This method return monthlySchedule from database
	 * @param date the java.util.Date of the month
	 * @return sg.edu.nus.iss.t4p.phoenix.entity.scalar.MonthlySchedule
	 * @exception sg.edu.nus.iss.t4p.phoenix.core.exceptions.BusinessLogicException
	 */
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
	
	
	
	/**
	 * This method return weekly schedule from database
	 * @param date the java.util.Date of the month
	 * @return sg.edu.nus.iss.t4p.phoenix.entity.scalar.WeeklySchedule
	 * @exception sg.edu.nus.iss.t4p.phoenix.core.exceptions.BusinessLogicException
	 */
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
	
	
	/**
	 * This method perform save and update of the program slot
	 * @param weeklySchedule sg.edu.nus.iss.t4p.phoenix.entity.scalar.WeeklySchedule
	 * @exception sg.edu.nus.iss.t4p.phoenix.core.exceptions.BusinessLogicException
	 * @see ScheduleService.validateSlot
	 */
	public void saveWeeklySlot(WeeklySchedule weeklySchedule) throws BusinessLogicException{
		try{
			this.validateSlot(weeklySchedule);
			DaoFactory.getInstance().getProgramSlotDao().persist(weeklySchedule.getProgramSlots());
			
		}catch(Exception e){
			if(e instanceof BusinessLogicException)
				throw (BusinessLogicException)e;
			
			throw new BusinessLogicException(e.getMessage());
		}
	}
	
	/**
	 * This method perform validation logic for program slot
	 * @param weeklySchedule sg.edu.nus.iss.t4p.phoenix.entity.scalar.WeeklySchedule
	 * @exception sg.edu.nus.iss.t4p.phoenix.core.exceptions.BusinessLogicException
	 */
	protected void validateSlot(WeeklySchedule weeklySchedule)throws BusinessLogicException{
		
		List<ProgramSlot> programSlots = weeklySchedule.getProgramSlots();
		for(int i = 0; i < programSlots.size(); i++ ){
			ProgramSlot programSlot = programSlots.get(i);
			if(!programSlot.isActive())
				continue;
			
			Date startDateTime = programSlot.getStartDateTime();
			Date endDateTime = programSlot.getEndDateTime();
			
			long interval = T4DateUtil.getIntervalInMinutes(startDateTime, endDateTime);
			if(interval < 30)
				throw new BusinessLogicException("Program slot must have at least 30 minutes length");
			
			// check within the list for non overlapping
			for(int x = i+1; x < programSlots.size(); x++){
				ProgramSlot comparedSlot = programSlots.get(x);
				if(!comparedSlot.isActive())
					continue;
				
				if(T4DateUtil.isOverlap(startDateTime, endDateTime, comparedSlot.getStartDateTime(), comparedSlot.getEndDateTime() ))
					throw new BusinessLogicException("Program slot time clash at " +T4DateUtil.DATE_TIME_01.format(startDateTime) + " to " + T4DateUtil.DATE_TIME_01.format(endDateTime));
				
			}
			// check if PK is not set, we need to look up the record from database.
		}
		
	}
	

}
