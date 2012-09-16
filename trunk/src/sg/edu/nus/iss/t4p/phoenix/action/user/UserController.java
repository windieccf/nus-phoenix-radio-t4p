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
	
	protected void doDisplay(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{

		User user = (UserDelegate.getInstance().retrieveUser(request.getParameter("username")));
		List<Role> roles = (RoleDelegate.getInstance().getRolesByUserId(user.getId()));
		request.setAttribute("users", user);
		request.setAttribute("roles", roles);
		request.getRequestDispatcher("/pages/user/maintain_user.jsp").forward(request, response);		
	}
	
	protected void doSave(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{
		
	}
}
