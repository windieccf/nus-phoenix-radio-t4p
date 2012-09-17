package sg.edu.nus.iss.t4p.phoenix.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class T4DateUtil {
	
	public static final DateFormat DATE_01 = new SimpleDateFormat("yyyy-MM-dd");
	public static final DateFormat DATE_02 = new SimpleDateFormat("dd-MM-yyyy");
	public static final DateFormat DATE_03 = new SimpleDateFormat("dd MMMM yyyy");
	
	public static Calendar rollToFirstDayOfWeek(Calendar cal){
		while(true){
			if(Calendar.MONDAY ==  cal.get(Calendar.DAY_OF_WEEK))
				break;
			
			cal.add(Calendar.DATE, -1);
		}
		return cal;
	}
	
	public static Calendar getCalendar(Date date){
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return cal;
	}
	
	public static Calendar getOneWeekLater(Date date){
		Calendar cal = T4DateUtil.getCalendar(date);
		cal.add(Calendar.DATE, 6);
		return cal;
	}
	
	public static Calendar getStartOfDay(Calendar cal){
    	cal.set(Calendar.HOUR_OF_DAY, 0);
    	cal.set(Calendar.MILLISECOND, 0);
    	cal.set(Calendar.SECOND, 0);
    	cal.set(Calendar.MINUTE, 0);
		return cal;
	}
	
	public static Calendar getEndOfDay(Calendar cal){
    	cal.set(Calendar.HOUR_OF_DAY, 23);
    	cal.set(Calendar.MILLISECOND, 999);
    	cal.set(Calendar.SECOND, 59);
    	cal.set(Calendar.MINUTE, 59);
		return cal;
	}
	
	public static java.sql.Date getSqlDate(Calendar cal){
		if(cal == null)
			throw new IllegalArgumentException("T4DateUtil.getSqlDate  cal must not be null");
		
		return new java.sql.Date(cal.getTime().getTime());
	}

}
