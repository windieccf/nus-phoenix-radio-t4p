package sg.edu.nus.iss.t4p.phoenix.service.security;

import java.sql.SQLException;

import sg.edu.nus.iss.t4p.phoenix.core.dao.DaoFactory;
import sg.edu.nus.iss.t4p.phoenix.core.exceptions.BusinessLogicException;
import sg.edu.nus.iss.t4p.phoenix.entity.user.User;

public class AuthenticateService {
	
	public User authenticateUser(User user) throws BusinessLogicException{
		String password = user.getPassword();
		
		user = DaoFactory.getInstance().getUserDao().getByUsername(user.getUsername());
		if(user == null)
			throw new BusinessLogicException("Invalid Username or Password");
		
		if(!user.getPassword().equals(password))
			throw new BusinessLogicException("Invalid Username or Password");
		
		try {
			user.setRoles(DaoFactory.getInstance().getRoleDao().getRolesByUserId(user.getId()) );
		} catch (SQLException e) {
			throw new BusinessLogicException("Unexpected Exception. Please contact module owner : Mansoor M I A0092661A" + e.getMessage());
		}
		
		return user;
	}

}
