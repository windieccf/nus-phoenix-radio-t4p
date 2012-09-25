package test.service.role;

import java.util.List;

import junit.framework.Assert;

import sg.edu.nus.iss.t4p.phoenix.entity.role.Role;

import sg.edu.nus.iss.t4p.phoenix.delegate.role.RoleDelegate;

import org.junit.Test;

public class RoleServiceTest {

	@Test
	public void testGetRolesByUserId() {

		List<Role> rolelist = null;
		
		rolelist = RoleDelegate.getInstance().getRolesByUserId(13L);
		
		System.err.println(rolelist.get(0).getRole());

		Assert.assertTrue(rolelist!=null);	
	}

}
