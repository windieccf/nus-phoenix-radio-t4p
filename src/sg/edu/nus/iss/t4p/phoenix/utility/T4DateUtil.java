package sg.edu.nus.iss.t4p.phoenix.utility;

import java.util.Calendar;

public class T4DateUtil {
	
	public static Calendar rollToFirstDayOfWeek(Calendar cal){
		while(true){
			if(Calendar.SUNDAY ==  cal.get(Calendar.DAY_OF_WEEK))
				break;
			
			cal.add(Calendar.DATE, -1);
		}
		return cal;
	}

}
