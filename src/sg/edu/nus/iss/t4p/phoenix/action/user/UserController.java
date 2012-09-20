package sg.edu.nus.iss.t4p.phoenix.action.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.edu.nus.iss.t4p.phoenix.core.action.BaseController;
import sg.edu.nus.iss.t4p.phoenix.core.exceptions.BusinessLogicException;
import sg.edu.nus.iss.t4p.phoenix.delegate.user.UserDelegate;
import sg.edu.nus.iss.t4p.phoenix.entity.user.User;
import sg.edu.nus.iss.t4p.phoenix.utility.T4StringUtil;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/userController/*")
public class UserController extends BaseController {
	
	protected void doList(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{
		// no pagination?
		
//		List<User> users = (UserDelegate.getInstance().retrieveUserList());
		List<User> users = (UserDelegate.getInstance().paginateUser(0L, 100L, new User() ));	
		
		
		request.setAttribute("users", users);
		request.getRequestDispatcher("/pages/user/list_user.jsp").forward(request, response);		
	}
	
	protected void doInit(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{
		String userName = request.getParameter("username");
		User user = new User();
		if (userName != null) {
			user = (UserDelegate.getInstance().retrieveUser(userName));
		} 
		request.setAttribute("user", user);
		request.getRequestDispatcher("/pages/user/maintain_user.jsp").forward(request, response);		
	}
	

	protected void doSave(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{
		User user = super.retrieveParameter(request,User.class);
		boolean saveStatus = false;
			try {
				if(T4StringUtil.isEmpty(user.getUsername()))
					throw new BusinessLogicException("Please fill in Username");
				saveStatus = (UserDelegate.getInstance().saveUser(user));
			} catch (BusinessLogicException e) {
				request.setAttribute("ERR", e.getMessage());
				request.setAttribute("user", user);
				request.getRequestDispatcher("/pages/user/maintain_user.jsp").forward(request, response);	
				e.printStackTrace();
			}
			
		if (saveStatus) {
			request.setAttribute("INF", "User saved successfully.");
			List<User> users = (UserDelegate.getInstance().paginateUser(0L, 100L, new User() ));
//			List<User> users = (UserDelegate.getInstance().retrieveUserList());
			
			request.setAttribute("users", users);
			request.getRequestDispatcher("/pages/user/list_user.jsp").forward(request, response);	
		} else {
			request.getRequestDispatcher("/pages/user/maintain_user.jsp").forward(request, response);
		}
	}
}
