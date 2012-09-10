package sg.edu.nus.iss.t4p.phoenix.service.security;

import sg.edu.nus.iss.t4p.phoenix.core.dao.DaoFactory;
import sg.edu.nus.iss.t4p.phoenix.core.exceptions.BusinessLogicException;
import sg.edu.nus.iss.t4p.phoenix.entity.user.User;

public class AuthenticateService {
	
	// TODO :: localize the message
	public User authenticateUser(String username, String password) throws BusinessLogicException{
		User user = DaoFactory.getInstance().getUserDao().getByUsername(username);
		if(user == null)
			throw new BusinessLogicException("Invalid Username or Password");
		
		if(!user.getPassword().equals(password))
			throw new BusinessLogicException("Invalid Username or Password");
		
		return user;
	}

}
