package sg.edu.nus.iss.t4p.phoenix.action.schedule;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.edu.nus.iss.t4p.phoenix.core.action.BaseController;
import sg.edu.nus.iss.t4p.phoenix.entity.scalar.MonthlySchedule;
import sg.edu.nus.iss.t4p.phoenix.entity.scalar.WeeklySchedule;
import sg.edu.nus.iss.t4p.phoenix.utility.T4DateUtil;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/scheduleController/*")
public class ScheduleController extends BaseController {

	protected void doList(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{
		// TODO :: listing by either week or month
		MonthlySchedule monthlySchedule = super.retrieveParameter(request, MonthlySchedule.class);
		
		Calendar cal = new GregorianCalendar();
		if(monthlySchedule.getMonth() == 0){
			monthlySchedule.setMonth(cal.get(Calendar.MONTH));
			monthlySchedule.setYear(cal.get(Calendar.YEAR));
			monthlySchedule.setMonthAsText(new SimpleDateFormat("MMMM").format(cal.getTime()));
		}
			
		// mock run on the UI
		cal.set(Calendar.DATE, 1);
		for(int i = 0; i < 6; i++){
			cal = T4DateUtil.rollToFirstDayOfWeek(cal);
			WeeklySchedule weeklySchedule = new WeeklySchedule();
			weeklySchedule.setStartDate(cal.getTime());
			weeklySchedule.setWeekNumber(cal.get(Calendar.WEEK_OF_YEAR));
			monthlySchedule.getWeekSchedules().add(weeklySchedule);
			cal.add(Calendar.DATE, 7);
			
		}
		request.setAttribute("monthlySchedule", monthlySchedule);
		request.getRequestDispatcher("/pages/schedule/list_schedule.jsp").forward(request, response);
	}
	
	protected void doSave(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{
		
	}
	
	
}
