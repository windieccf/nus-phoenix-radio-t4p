package sg.edu.nus.iss.t4p.phoenix.action.radioprogram;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.edu.nus.iss.t4p.phoenix.core.action.BaseController;
import sg.edu.nus.iss.t4p.phoenix.delegate.radioprogram.RadioProgramDelegate;
import sg.edu.nus.iss.t4p.phoenix.entity.radioprogram.RadioProgram;


@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/radioprogramController/*")
public class RadioProgramController extends BaseController {
	protected void doList(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{

		List<RadioProgram> radioprograms = RadioProgramDelegate.getInstance().retrieveProgramList();
				
		request.setAttribute("radioprograms", radioprograms);
		request.getRequestDispatcher("/pages/radioprogram/list_radioprogram.jsp").forward(request, response);		
	}
	
	/*
	protected void doDisplay(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{
        RadioProgram program = (RadioProgramDelegate.getInstance()).getByProgramName(request.getParameter("programName"));
		request.setAttribute("programList",program);
		request.getRequestDispatcher("/pages/radioprogram/view_radioprogram.jsp").forward(request, response);		
	}
	*/
}
