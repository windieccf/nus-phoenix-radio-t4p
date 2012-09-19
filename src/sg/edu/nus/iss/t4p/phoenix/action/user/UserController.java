package sg.edu.nus.iss.t4p.phoenix.action.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.edu.nus.iss.t4p.phoenix.core.action.BaseController;
import sg.edu.nus.iss.t4p.phoenix.delegate.role.RoleDelegate;
import sg.edu.nus.iss.t4p.phoenix.delegate.user.UserDelegate;
import sg.edu.nus.iss.t4p.phoenix.entity.role.Role;
import sg.edu.nus.iss.t4p.phoenix.entity.user.User;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/userController/*")
public class UserController extends BaseController {
	
	protected void doList(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{
		// no pagination?
		
		List<User> users = (UserDelegate.getInstance().retrieveUserList());
				
		request.setAttribute("users", users);
		request.getRequestDispatcher("/pages/user/list_user.jsp").forward(request, response);		
	}
	
	protected void doInit(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{

		User user = (UserDelegate.getInstance().retrieveUser(request.getParameter("username")));
		List<Role> roles = (RoleDelegate.getInstance().getRolesByUserId(user.getId()));
		request.setAttribute("users", user);
		request.setAttribute("roles", roles);
		request.getRequestDispatcher("/pages/user/maintain_user.jsp").forward(request, response);		
	}
	
	protected void doSave(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{
		User user = super.retrieveParameter(request,User.class);
		boolean saveStatus = (UserDelegate.getInstance().saveUser(user));
		if (saveStatus) {
			request.setAttribute("INF", "User saved successfully.");
			List<User> users = (UserDelegate.getInstance().retrieveUserList());
			
			request.setAttribute("users", users);
			request.getRequestDispatcher("/pages/user/list_user.jsp").forward(request, response);	
		} else {
			request.getRequestDispatcher("/pages/user/maintain_user.jsp").forward(request, response);
		}
	}
}
