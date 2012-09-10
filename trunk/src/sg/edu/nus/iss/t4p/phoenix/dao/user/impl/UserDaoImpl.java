package sg.edu.nus.iss.t4p.phoenix.dao.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sg.edu.nus.iss.t4p.phoenix.core.dao.impl.BaseDao;
import sg.edu.nus.iss.t4p.phoenix.dao.user.UserDao;
import sg.edu.nus.iss.t4p.phoenix.entity.user.User;

public class UserDaoImpl extends BaseDao<User> implements UserDao{
	
	private static UserDaoImpl instance;
	private UserDaoImpl(){
		/*ENSURE SINGLETON*/
		super();
	}
	
	public static UserDao getInstance(){
		if(instance == null)
			instance = new UserDaoImpl();
		
		return instance;
	}

	@Override
	public User getByUsername(String username) {
		
		try( Connection con = super.getConnection() ){
			StringBuffer mySql = new StringBuffer("SELECT * FROM ")
								.append( super.getTableName(User.class.getName()) + " WHERE UPPER(USERNAME) = ? ");

			PreparedStatement stmt = con.prepareStatement(mySql.toString());
			stmt.setString(1, username.toUpperCase());

			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				User user = super.createValueObject();
				super.assignValue(user, user.getColumnField(), rs);
				return user;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	/*public static void main(String... args){
		UserDaoImpl impl = new UserDaoImpl();
		User user = impl.getByUsername("robin");
		System.err.println(user.getUsername());
		user.toString();
	}*/

}
