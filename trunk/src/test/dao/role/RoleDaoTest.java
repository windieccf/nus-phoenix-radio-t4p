package test.dao.role;


import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import junit.framework.Assert;

import sg.edu.nus.iss.t4p.phoenix.core.dao.DaoFactory;
import sg.edu.nus.iss.t4p.phoenix.entity.role.*;



public class RoleDaoTest {

//	@Test
	public void testGetRolesByUserId() {
		Long radioprogramId = 5L;
		List<Role> rolelist = null;
		
		try{
			rolelist = DaoFactory.getInstance().getRoleDao().getRolesByUserId(radioprogramId);
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.assertTrue(rolelist!=null);
		}
		
		Assert.assertTrue(rolelist!=null);
	}
	
	
	@Test
	public void testGetRoles() {
		List<Role> rolelist = null;
		
		try{
			rolelist = DaoFactory.getInstance().getRoleDao().getRoles();
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.assertTrue(rolelist!=null);
		}
		System.err.println(rolelist.get(0).getRole());
		Assert.assertTrue(rolelist!=null);
	}
}
