package sg.edu.nus.iss.t4p.phoenix.delegate.role;

import java.util.List;

import sg.edu.nus.iss.t4p.phoenix.entity.role.Role;
import sg.edu.nus.iss.t4p.phoenix.service.role.RoleService;

public class RoleDelegate {
	public static RoleDelegate instance;
	private RoleDelegate(){/*SINGLETON*/}
	public static RoleDelegate getInstance(){
		if(instance == null)
			instance = new RoleDelegate();
		
		return instance;
	}
	public List<Role> getRolesByUserId(Long userId) {
		return (RoleService.getInstance().getRolesByUserId(userId));
	}
	

}
