package sg.edu.nus.iss.t4p.phoenix.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class T4DateUtil {
	
	public static final DateFormat DATE_01 = new SimpleDateFormat("yyyy-MM-dd");
	public static final DateFormat DATE_02 = new SimpleDateFormat("dd-MM-yyyy");
	public static final DateFormat DATE_03 = new SimpleDateFormat("dd MMMM yyyy");
	
	public static final DateFormat DATE_TIME_01 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	
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
	
	public static Calendar alignDateTimeByDayOfWeek(Date date01, Date date02){
		Calendar cal01 = T4DateUtil.getCalendar(date01);
		Calendar cal02 = T4DateUtil.getCalendar(date02);
		
		cal01.set(Calendar.DAY_OF_WEEK, cal02.get(Calendar.DAY_OF_WEEK));
		cal01.set(Calendar.HOUR_OF_DAY, cal02.get(Calendar.HOUR_OF_DAY));
		cal01.set(Calendar.MINUTE, cal02.get(Calendar.MINUTE));
		cal01.set(Calendar.SECOND, cal02.get(Calendar.SECOND));
		return cal01;
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
	
	public static long getIntervalInMinutes(Date dateFrom, Date dateTo){
		long difference =  dateTo.getTime() - dateFrom.getTime(); 
		long minutes = (long) ((difference / (1000*60)) );
		return minutes;
	}
	
	
	public static java.sql.Date getSqlDate(Calendar cal){
		if(cal == null)
			throw new IllegalArgumentException("T4DateUtil.getSqlDate  cal must not be null");
		
		return new java.sql.Date(cal.getTime().getTime());
	}
	
	public static boolean isOverlap(Date dateFrom, Date dateTo, Date compareDateFrom, Date compareDateTo){
		if(dateFrom.compareTo(compareDateFrom) == 0 || dateTo.compareTo(compareDateTo) == 0)
			return true;
		
		if(dateFrom.before(compareDateFrom))
			return dateTo.after(compareDateFrom);
		
		if(dateFrom.after(compareDateFrom))
			return dateFrom.before(compareDateTo);
		
		return false;
	}
	
	public static Date saveParseDate(String text) throws ParseException{
		try{
			return DATE_TIME_01.parse(text);
		}catch(ParseException e){
			return DATE_02.parse(text);	
		}
	}

}
