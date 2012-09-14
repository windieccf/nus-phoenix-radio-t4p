package sg.edu.nus.iss.t4p.phoenix.delegate.user;

import java.util.ArrayList;

import sg.edu.nus.iss.t4p.phoenix.entity.user.User;
import sg.edu.nus.iss.t4p.phoenix.service.user.UserService;

public class UserDelegate {
	public static UserDelegate instance;
	private UserDelegate(){/*SINGLETON*/}
	public static UserDelegate getInstance(){
		if(instance == null)
			instance = new UserDelegate();
		
		return instance;
	}
	public ArrayList<User> retrieveUserList() {
		return (UserService.getInstance().retrieveUserList());
	}
	public User retrieveUser(String username) {
		return (UserService.getInstance().retrieveUser(username));
	}

}