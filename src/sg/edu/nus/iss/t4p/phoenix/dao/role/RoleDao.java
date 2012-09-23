package sg.edu.nus.iss.t4p.phoenix.dao.role;

import java.sql.SQLException;
import java.util.List;

import sg.edu.nus.iss.t4p.phoenix.core.dao.BaseDaoIntf;
import sg.edu.nus.iss.t4p.phoenix.entity.role.Role;

public interface RoleDao extends BaseDaoIntf<Role>{
	
	public List<Role> getRolesByUserId(Long userId) throws SQLException;
	public List<Role> getRoles() throws SQLException;
}
