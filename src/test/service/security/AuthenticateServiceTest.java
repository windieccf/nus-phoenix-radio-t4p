package test.service.security;


import junit.framework.Assert;

import org.junit.Test;

import sg.edu.nus.iss.t4p.phoenix.core.exceptions.BusinessLogicException;
import sg.edu.nus.iss.t4p.phoenix.delegate.security.AuthenticateDelegate;
import sg.edu.nus.iss.t4p.phoenix.entity.user.User;

public class AuthenticateServiceTest {

	@Test
	public void testAuthenticateUser() {
		User user = null;
		User user2 = null;
		long userId = 13L;
		user = new User();
		user.setId(userId);
		user.setUsername("songlei");
		user.setPassword("password");
		
		try{
			user2 = new AuthenticateDelegate().authenticateUser(user);
			System.err.println(user2.getUsername());
			System.err.println(user2.getPassword());
			Assert.assertTrue(user2!=null);	
		}catch (BusinessLogicException e) {
			e.printStackTrace();
		}
		
		Assert.assertTrue(user2!=null);	
	}

}
