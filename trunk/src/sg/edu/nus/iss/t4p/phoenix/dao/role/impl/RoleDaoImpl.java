package sg.edu.nus.iss.t4p.phoenix.dao.role.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import sg.edu.nus.iss.t4p.phoenix.core.dao.impl.BaseDao;
import sg.edu.nus.iss.t4p.phoenix.dao.role.RoleDao;
import sg.edu.nus.iss.t4p.phoenix.entity.role.Role;
import sg.edu.nus.iss.t4p.phoenix.entity.user.User;

public class RoleDaoImpl extends BaseDao<Role> implements RoleDao{
	
	private static RoleDaoImpl instance;
	private RoleDaoImpl(){/*SINGLETON*/}
	public static RoleDaoImpl getInstance(){
		if(instance == null)
			instance = new RoleDaoImpl();
		
		return instance;
	}
	@Override
	public List<Role> getRolesByUserId(Long userId) throws SQLException {
		return null;
//		List<Role> roles = null;
//		try(Connection con = super.getConnection()){
//			select R.* from USER_ROLE UR INNER JOIN ROLE R ON 
//				WHERE USER_ID = ?
//			
//						
//						
//						roles = new ArrayList<Role>();			
//			ResultSet rs = stmt.executeQuery();
//			if(rs.next()){
//				Role role = super.createValueObject();
//				super.assignValue(role, role.getColumnField(), rs);
//				roles.add(role);
////				return role;
//			}			
//						
//			
//			
//		}catch(Exception e){
//			throw e;
//		}
//		
//		return roles;
//	}

	}}
