package test.dao.user;

import java.sql.SQLException;

import junit.framework.Assert;

import org.junit.Test;

import sg.edu.nus.iss.t4p.phoenix.core.dao.DaoFactory;
import sg.edu.nus.iss.t4p.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.t4p.phoenix.entity.user.User;


public class UserDaoTest {
	
	@Test
	public void retrieveUserById(){
		Long userId = 1L;
		User user = null;
		try {
			user = DaoFactory.getInstance().getUserDao().getObject(String.valueOf(userId));
			System.err.println(user.getUsername());
			Assert.assertTrue(user.getId() != null);
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(user.getId() != null);
	}
	
	@Test
	public void retrieveUserByObject(){
		Long userId = 1L;
		User user = null;
		try {
			user = new User();
			user.setId(userId);
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
	
	

	@Test
	public void retrieveCorrectUser(){
		String username = "robin";
		User user = DaoFactory.getInstance().getUserDao().getByUsername(username);
		Assert.assertTrue(username.equals(user.getUsername()));
	}
	
}
