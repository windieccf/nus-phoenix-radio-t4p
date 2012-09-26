package test.service.user;

import org.junit.Test;

import java.util.List;

import junit.framework.Assert;

import sg.edu.nus.iss.t4p.phoenix.core.exceptions.BusinessLogicException;
import sg.edu.nus.iss.t4p.phoenix.entity.user.User;
import sg.edu.nus.iss.t4p.phoenix.delegate.user.UserDelegate;

public class UserServiceTest {

	//@Test
	public void testRetrieveUserList() {
		List<User> userlist = null;
		
		userlist = UserDelegate.getInstance().retrieveUserList();

		Assert.assertNotNull(userlist);
	}

	
	//@Test
	public void testPaginateUser() {
		List<User> userlist = null;
		
		User user = new User();
		user.setId(1L);
		user.setUsername("admin");
		
		userlist = UserDelegate.getInstance().paginateUser(1L, 1L, user);
		
		Assert.assertNotNull(userlist);
	}
	
	//@Test
	public void testRetrieveUser() {
		User user = null;
		
		user = UserDelegate.getInstance().retrieveUser("songlei");
		
		Assert.assertNotNull(user);
	}
	
	//@Test
	public void testSaveUser() {
		boolean result = false;
		User user = new User();
		user.setId(16L);
		user.setUsername("songlei001");
		user.setPassword("password");
		
		try {
			result = UserDelegate.getInstance().saveUser(user);
			Assert.assertTrue(result);
		} catch (BusinessLogicException e) {
			e.printStackTrace();
			Assert.fail();
		}
		
		Assert.assertTrue(result);
	}
	
	@Test
	public void testRetrievePresenterProducerList() {
		List<User> presenterproducers = null;
		
		presenterproducers = UserDelegate.getInstance().retrievePresenterProducerList(true);
		
		Assert.assertNotNull(presenterproducers);
	}
}
