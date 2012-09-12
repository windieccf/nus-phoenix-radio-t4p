package sg.edu.nus.iss.t4p.phoenix.entity.scalar;

import java.util.ArrayList;
import java.util.List;

public class MonthlySchedule {
	
	private int month;
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
	
}
