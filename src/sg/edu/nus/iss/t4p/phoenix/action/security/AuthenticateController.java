package sg.edu.nus.iss.t4p.phoenix.action.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.edu.nus.iss.t4p.phoenix.core.action.BaseController;
import sg.edu.nus.iss.t4p.phoenix.core.exceptions.BusinessLogicException;
import sg.edu.nus.iss.t4p.phoenix.delegate.security.AuthenticateDelegate;
import sg.edu.nus.iss.t4p.phoenix.entity.user.User;
import sg.edu.nus.iss.t4p.phoenix.utility.T4StringUtil;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/authenticateController/*")
public class AuthenticateController extends BaseController {
	
	protected void doLogin(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{
		User user = super.retrieveParameter(request,User.class);
		try{
			if(T4StringUtil.isEmpty(user.getUsername()) || T4StringUtil.isEmpty(user.getPassword()))
				throw new BusinessLogicException("Please fill in Username and Password");
			
			user = (new AuthenticateDelegate()).authenticateUser(user.getUsername(), user.getPassword());
			if(user!= null)
				request.getSession().setAttribute("user", user);
		
			super.doRedirect(request, response, "/");
		}catch(Exception e){
			request.setAttribute("ERR", e.getMessage());
			request.getRequestDispatcher("/pages/login/login.jsp").forward(request, response);
		}
	}
	
	protected void doLogout(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{
		request.getSession().invalidate();
		request.getRequestDispatcher("/").forward(request, response);
	}
	
	

}
