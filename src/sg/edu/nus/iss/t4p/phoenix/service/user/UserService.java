package sg.edu.nus.iss.t4p.phoenix.service.user;

import java.util.ArrayList;

import sg.edu.nus.iss.t4p.phoenix.core.dao.DaoFactory;
import sg.edu.nus.iss.t4p.phoenix.entity.user.User;

public class UserService {
	public static UserService instance;
	private UserService(){/*SINGELTON*/}
	public static UserService getInstance(){
		if(instance == null)
			instance = new UserService();
		
		return instance;
	}
	public ArrayList<User> retrieveUserList() {
		ArrayList<User> users = DaoFactory.getInstance().getUserDao().retrieveUserList();
		
		return users;
	}
	public User retrieveUser(String username) {
		User user = DaoFactory.getInstance().getUserDao().getByUsername(username);
		
		return user;
	}
}
