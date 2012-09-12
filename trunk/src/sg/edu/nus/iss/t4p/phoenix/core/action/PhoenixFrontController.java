package sg.edu.nus.iss.t4p.phoenix.core.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.edu.nus.iss.t4p.phoenix.core.enumeration.UrlPathEnum;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/controller/*")
public class PhoenixFrontController extends BaseController {

	
	@Override
	protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		request.setAttribute("comingFrom", "PhoenixFrontController");
		request.getRequestDispatcher(  UrlPathEnum.getByControlPath(pathInfo).getDestinationPath()  ).forward(request, response);
	}

}
