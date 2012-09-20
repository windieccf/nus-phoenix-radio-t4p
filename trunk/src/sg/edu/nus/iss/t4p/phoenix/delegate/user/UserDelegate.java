package sg.edu.nus.iss.t4p.phoenix.delegate.user;

import java.util.List;

import sg.edu.nus.iss.t4p.phoenix.core.exceptions.BusinessLogicException;
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
	public List<User> retrieveUserList() {
		return (UserService.getInstance().retrieveUserList());
	}
	
	public List<User> paginateUser(Long pageNo , Long rowPerPage, User user) {
		return (UserService.getInstance().paginateUser(pageNo , rowPerPage , user));
	}
	
	public User retrieveUser(String username) {
		return (UserService.getInstance().retrieveUser(username));
	}
	public boolean saveUser(User user) throws BusinessLogicException {
		return (UserService.getInstance().saveUser(user));
	}
	
	public List<User> retrievePresenterProducerList(boolean IsPresenter){
		return UserService.getInstance().retrievePresenterProducerList(IsPresenter);
	}

}