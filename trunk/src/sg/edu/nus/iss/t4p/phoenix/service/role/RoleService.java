package sg.edu.nus.iss.t4p.phoenix.service.role;

import java.sql.SQLException;
import java.util.List;

import sg.edu.nus.iss.t4p.phoenix.core.dao.DaoFactory;
import sg.edu.nus.iss.t4p.phoenix.entity.role.Role;

public class RoleService {
	public static RoleService instance;
	private RoleService(){/*SINGELTON*/}
	public static RoleService getInstance(){
		if(instance == null)
			instance = new RoleService();
		
		return instance;
	}
	public List<Role> getRolesByUserId(Long userId)  {
		List<Role> roles = null;
		try {
			roles = DaoFactory.getInstance().getRoleDao().getRolesByUserId(userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roles;
	}
	
}
