package sg.edu.nus.iss.t4p.phoenix.action.schedule;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.edu.nus.iss.t4p.phoenix.core.action.BaseController;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/scheduleController/*")
public class ScheduleController extends BaseController {

	protected void doList(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{
		// TODO :: listing by either week or month
		
		request.getRequestDispatcher("/pages/scheduler/scheduler.jsp").forward(request, response);
	}
	
	protected void doSave(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{
		
	}
	
	
}
