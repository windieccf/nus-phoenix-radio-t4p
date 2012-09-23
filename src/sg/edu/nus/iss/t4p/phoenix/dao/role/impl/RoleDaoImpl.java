package sg.edu.nus.iss.t4p.phoenix.dao.role.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.t4p.phoenix.core.dao.impl.BaseDao;
import sg.edu.nus.iss.t4p.phoenix.dao.role.RoleDao;
import sg.edu.nus.iss.t4p.phoenix.entity.role.Role;

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
		List<Role> roles = null;
		try(Connection con = super.getConnection()){
			String mySql = "select R.* from USER_ROLE UR INNER JOIN ROLE R ON UR.ROLE_ID = R.ID WHERE USER_ID = ?";
				PreparedStatement stmt = con.prepareStatement(mySql.toString());
				stmt.setLong(1, userId);

				ResultSet rs = stmt.executeQuery();
				roles = new ArrayList<Role>();			
				
			while(rs.next()){
				Role role = super.createValueObject();
				super.assignValue(role, role.getColumnField(), rs);
				roles.add(role);
			}			
			return roles;
						
			
			
		}catch(Exception e){
			throw e;
		}
		
	}
	
	public List<Role> getRoles() throws SQLException {
		List<Role> roles = null;
		try(Connection con = super.getConnection()){
			String mySql = "select R.* from ROLE R";
				PreparedStatement stmt = con.prepareStatement(mySql.toString());

				ResultSet rs = stmt.executeQuery();
				roles = new ArrayList<Role>();			
				
			while(rs.next()){
				Role role = super.createValueObject();
				super.assignValue(role, role.getColumnField(), rs);
				roles.add(role);
			}			
			return roles;
						
			
			
		}catch(Exception e){
			throw e;
		}
		
	}
}
