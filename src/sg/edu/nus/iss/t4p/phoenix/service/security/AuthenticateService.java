package sg.edu.nus.iss.t4p.phoenix.service.security;

import sg.edu.nus.iss.t4p.phoenix.core.dao.DaoFactory;
import sg.edu.nus.iss.t4p.phoenix.core.exceptions.BusinessLogicException;
import sg.edu.nus.iss.t4p.phoenix.entity.user.User;

public class AuthenticateService {
	
	// TODO :: localize the message
	public User authenticateUser(User user) throws BusinessLogicException{
		String password = user.getPassword();
		
		user = DaoFactory.getInstance().getUserDao().getByUsername(user.getUsername());
		if(user == null)
			throw new BusinessLogicException("Invalid Username or Password");
		
		if(!user.getPassword().equals(password))
			throw new BusinessLogicException("Invalid Username or Password");
		
		return user;
	}

}
