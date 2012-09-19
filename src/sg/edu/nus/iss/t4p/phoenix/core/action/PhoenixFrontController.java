package sg.edu.nus.iss.t4p.phoenix.core.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.edu.nus.iss.t4p.phoenix.core.enumeration.UrlPathEnum;

/**
* Class for Front End controller pattern , perform controller JSP URL to Actual Path conversion
* @author Robin Foe A0092657U
* @version 1.0
*/

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/controller/*")
public class PhoenixFrontController extends BaseController {

	/**
	 * This method process the request from URL and redirect it to appropriate Path
	 * @param request the HttpServletRequest
	 * @param response the HttpServletResponse
	 * @exception javax.servlet.ServletException
	 * @exception java.io.IOException
	 * @see javax.servlet.ServletException
	 * @see java.io.IOException
	 */
	@Override
	protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		request.getRequestDispatcher(  UrlPathEnum.getByControlPath(pathInfo).getDestinationPath()  ).forward(request, response);
	}

}
