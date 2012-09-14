package sg.edu.nus.iss.t4p.phoenix.dao.user;

import java.util.ArrayList;

import sg.edu.nus.iss.t4p.phoenix.core.dao.BaseDaoIntf;
import sg.edu.nus.iss.t4p.phoenix.entity.user.User;

public interface UserDao extends BaseDaoIntf<User>{
	
	public User getByUsername(String username);
	public ArrayList<User> retrieveUserList();

}
