package sg.edu.nus.iss.t4p.phoenix.action.schedule;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.edu.nus.iss.t4p.phoenix.core.action.BaseController;
import sg.edu.nus.iss.t4p.phoenix.core.constant.ConstantAttribute;
import sg.edu.nus.iss.t4p.phoenix.core.exceptions.BusinessLogicException;
import sg.edu.nus.iss.t4p.phoenix.delegate.schedule.ScheduleDelegate;
import sg.edu.nus.iss.t4p.phoenix.entity.scalar.MonthlySchedule;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/scheduleController/*")
public class ScheduleController extends BaseController {

	protected void doList(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{
		// TODO :: listing by either week or month
		MonthlySchedule monthlySchedule = super.retrieveParameter(request, MonthlySchedule.class);
		if(monthlySchedule.isMonthlyView())
			this.doPrepareMonthlyView(request, response, monthlySchedule);
		
		request.getRequestDispatcher("/pages/schedule/list_schedule.jsp").forward(request, response);
	}
	
	private void doPrepareMonthlyView(HttpServletRequest request,	HttpServletResponse response , MonthlySchedule monthlySchedule) throws ServletException, IOException{
		
		Calendar cal = new GregorianCalendar();
		if(monthlySchedule.getMonth() == -2){
			monthlySchedule.setMonth(cal.get(Calendar.MONTH));
			monthlySchedule.setYear(cal.get(Calendar.YEAR));
		}else{
			cal.set(Calendar.YEAR, monthlySchedule.getYear());
			if(monthlySchedule.getMonth() == -1){
				cal.add(Calendar.YEAR, -1);
				cal.set(Calendar.MONTH, 11);
			}else if(monthlySchedule.getMonth() == 12){
				cal.add(Calendar.YEAR, 1);
				cal.set(Calendar.MONTH, 0);
			}else{
				cal.set(Calendar.MONTH, monthlySchedule.getMonth());
			}
		}

		try {
			monthlySchedule = ScheduleDelegate.getInstance().getMonthlySlotByDate(cal.getTime());
		} catch (BusinessLogicException e) {
			e.printStackTrace();
			request.setAttribute(ConstantAttribute.MESSAGE_ERR, e.getMessage());
		}
		request.setAttribute("monthlySchedule", monthlySchedule);
		
	}
	
	protected void doSave(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{
		
	}
	
	
}
