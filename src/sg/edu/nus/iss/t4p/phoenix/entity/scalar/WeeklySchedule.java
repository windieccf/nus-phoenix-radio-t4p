package sg.edu.nus.iss.t4p.phoenix.entity.scalar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import sg.edu.nus.iss.t4p.phoenix.entity.schedule.ProgramSlot;
import sg.edu.nus.iss.t4p.phoenix.utility.T4StringUtil;

public class WeeklySchedule {
	
	@SuppressWarnings("unchecked")
	public WeeklySchedule(){
		// declared as 7, Monday to Sunday
		weekSlot = new List[7];
		for(int i = 0; i  < 7; i++){
			weekSlot[i] = new ArrayList<ProgramSlot>();
		}
		
	}
	
	private int weekNumber;
	public int getWeekNumber() {return weekNumber;}
	public void setWeekNumber(int weekNumber) {this.weekNumber = weekNumber;}
	
	private Date startDate;
	public Date getStartDate() {return startDate;}
	public void setStartDate(Date startDate) {this.startDate = startDate;}
	
	private int[] programCount;
	public int[] getProgramCount() {return programCount;}
	public void setProgramCount(int[] programCount) {this.programCount = programCount;}
	
	private List<ProgramSlot>[] weekSlot;
	public List<ProgramSlot>[] getWeekSlot() {return weekSlot;}
	public void setWeekSlot(List<ProgramSlot>[] weekSlot) {this.weekSlot = weekSlot;}
	
	
	/** Cosmetic related function. Not in USE CASE DIAGRAM **********************************************/
	public String getDateDisplay(int ctr){
		Calendar cal = new GregorianCalendar();
		cal.setTime(startDate);
		cal.add(Calendar.DATE, ctr);
		return T4StringUtil.FORMAT_01.format(cal.get(Calendar.DATE));
	}
	
	public boolean isSameMonth(int ctr, int currMonth){
		Calendar cal = new GregorianCalendar();
		cal.setTime(startDate);
		cal.add(Calendar.DATE, ctr);
		return (cal.get(Calendar.MONTH) == currMonth);
	}
	
	
	public String getTotalRunningTime(){
		return "0";
	}

}
