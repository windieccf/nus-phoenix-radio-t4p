package sg.edu.nus.iss.t4p.phoenix.entity.scalar;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.t4p.phoenix.core.constant.ConstantSchedulerViewMode;

public class MonthlySchedule {
	
	private int month= -2;
	public int getMonth() {return month;}
	public void setMonth(int month) {this.month = month;}
	
	private int year;
	public int getYear() {return year;}
	public void setYear(int year) {this.year = year;}
	
	private List<WeeklySchedule> weekSchedules = new ArrayList<WeeklySchedule>();
	public List<WeeklySchedule> getWeekSchedules() {return weekSchedules;}
	public void setWeekSchedules(List<WeeklySchedule> weekSchedules) {this.weekSchedules = weekSchedules;}

	private String monthAsText;
	public String getMonthAsText() {return monthAsText;}
	public void setMonthAsText(String monthAsText) {this.monthAsText = monthAsText;}
	
	
	/** UI related variable, non usecase ********************************************/
	private String viewMode = ConstantSchedulerViewMode.MONTH;
	public String getViewMode() {return viewMode;}
	public void setViewMode(String viewMode) {this.viewMode = viewMode;}
	public boolean isMonthlyView(){return ConstantSchedulerViewMode.MONTH.equals(this.getViewMode());}
	
	
	
}
