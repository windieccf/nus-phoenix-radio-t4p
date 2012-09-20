package sg.edu.nus.iss.t4p.phoenix.service.user;

import java.sql.SQLException;
import java.util.List;

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
	public List<User> retrieveUserList()  {
		List<User> users = null;
		try {
			users = DaoFactory.getInstance().getUserDao().loadAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	public User retrieveUser(String username) {
		User user = DaoFactory.getInstance().getUserDao().getByUsername(username);
		try {
			user.setRoles(DaoFactory.getInstance().getRoleDao().getRolesByUserId(user.getId()) );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		 // user
		
		return user;
	}
	public boolean saveUser(User user) {
		boolean saveStatus = DaoFactory.getInstance().getUserDao().saveUser(user);	
		return saveStatus;
	}
	
    public List<User> retrievePresenterProducerList(boolean IsPresenter){
		List<User> presenterproducers = null;
		try{
			presenterproducers = DaoFactory.getInstance().getUserDao().retrievePresenterProducerList(IsPresenter);
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return presenterproducers;
	}
    
	public boolean isUserExisting(User user) {
		return DaoFactory.getInstance().getUserDao().isUserExisting(user);	
	}
}
