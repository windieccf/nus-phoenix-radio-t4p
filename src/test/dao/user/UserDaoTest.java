package test.dao.user;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import sg.edu.nus.iss.t4p.phoenix.core.dao.DaoFactory;
import sg.edu.nus.iss.t4p.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.t4p.phoenix.entity.user.User;


public class UserDaoTest {
	
	//@Test
	public void testGetByUsername(){
		User user = null;
		
		user =  DaoFactory.getInstance().getUserDao().getByUsername("songlei");
		System.err.println(user.getUsername());
		Assert.assertTrue(user.getUsername().equals("songlei"));
	}
	
	//@Test
	public void testRetrieveUserList(){
		
		ArrayList<User> userlist = null;
		userlist = DaoFactory.getInstance().getUserDao().retrieveUserList();
		System.err.println(userlist.get(0).getUsername());
			
		Assert.assertTrue(userlist != null);
	}
	
	//@Test
	public void testSaveUser(){
		User user = null;
		long userId = 16L;
		boolean result;
		user = new User();
		user.setId(userId);
		user.setFirstName("Leiiii");
		user.setLastName("Song");
		user.setUsername("songleiiii");
		
		result = DaoFactory.getInstance().getUserDao().saveUser(user);
			
		System.err.println(result);
		Assert.assertTrue(result);			
	}
	
	//@Test
	public void testIsUserExisting(){
		User user = null;
		long userId = 13L;
		boolean result;
		user = new User();
		user.setId(userId);
		user.setFirstName("lei");
		user.setLastName("song");
		user.setUsername("songlei");
	
		result = DaoFactory.getInstance().getUserDao().isUserExisting(user);
		
		System.err.println(result);
		Assert.assertTrue(result);	
		
	}
	
	//@Test
	public void testRetrieveUserById(){
		Long userId = 1L;
		User user = null;
		try {
			user = DaoFactory.getInstance().getUserDao().getObject(String.valueOf(userId));
			System.err.println(user.getUsername());
			Assert.assertTrue(user.getUsername().equals("songlei"));
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(user.getUsername().equals("songlei"));
	}
	
	//@Test
	public void testRetrieveUserByObject(){
		Long userId = 5L;
		User user = null;
		try {
			user = new User();
			user.setId(userId);
			user.setFirstName("Leiiii");
			user.setLastName("Song");
			user.setUsername("songleiiii");
			
			DaoFactory.getInstance().getUserDao().load(user);
			System.err.println(user.getUsername());
			Assert.assertTrue(user.getUsername() != null);
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(user.getId() != null);
	}
	
	
	//@Test
	public void retrieveCorrectUser(){
		String username = "robin";
		User user = DaoFactory.getInstance().getUserDao().getByUsername(username);
		Assert.assertTrue(username.equals(user.getUsername()));
	}
	
	
//	@Test
	public void insertUser(){
		User user = new User();
		try {
			user.setUsername("robin");
			user.setPassword("password");
			user.setContactHome("93805110");
			user.setContactMobile("91157054");
			user.setAddress("singapore");
			user.setEmail("WWW@www.com");
			user.setLastName("foe");
			user.setFirstName("Robin");
			user.setDateOfBirth(new Date());
			try {
				DaoFactory.getInstance().getUserDao().persist(user);
			} catch (NotFoundException e) {
				e.printStackTrace();
			}
			System.err.println(user.getId());
			
			//user = DaoFactory.getInstance().getUserDao().getObject(String.valueOf(userId));
//			System.err.println(user.getUsername());
//			Assert.assertTrue(user.getId() != null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		Assert.assertTrue(user.getId() != null);
	}
	
	@Test
	
	public void testRetrievePresenterProducerList(){
		ArrayList<User> userlist = null;
		boolean isPresenter = true;
		
		userlist = DaoFactory.getInstance().getUserDao().retrievePresenterProducerList(isPresenter);
		System.err.println(userlist.get(0).getUsername());
			
		Assert.assertTrue(userlist != null);
		
	}
	
}
