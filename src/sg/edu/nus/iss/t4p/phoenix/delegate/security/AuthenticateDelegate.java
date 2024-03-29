package sg.edu.nus.iss.t4p.phoenix.delegate.security;

import sg.edu.nus.iss.t4p.phoenix.core.exceptions.BusinessLogicException;
import sg.edu.nus.iss.t4p.phoenix.entity.user.User;
import sg.edu.nus.iss.t4p.phoenix.service.security.AuthenticateService;

public class AuthenticateDelegate {
	
	public User authenticateUser(User user) throws BusinessLogicException{
		return (new AuthenticateService()).authenticateUser(user);
	}

}
