package sg.edu.nus.iss.t4p.phoenix.core.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/controller/*")
public class PhoenixFrontController extends BaseController {

	private static Map<String,String> URL_MAP;
	static{
		URL_MAP = new HashMap<String,String>();
		URL_MAP.put("/authenticate.do", "/authenticateController/login.do");
		URL_MAP.put("/logout.do", "/authenticateController/logout.do");
	}
	
	
	
	@Override
	protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		request.setAttribute("comingFrom", "PhoenixFrontController");
		request.getRequestDispatcher(this.decidePath(pathInfo)).forward(request, response);
//		RequestDispatcher rd = getServletContext().getRequestDispatcher(this.decidePath(pathInfo));
//		rd.forward(request, response);
	}
	
	private String decidePath(String controlPath){
		return (URL_MAP.containsKey(controlPath)) ? URL_MAP.get(controlPath) : "/";
	}

}
