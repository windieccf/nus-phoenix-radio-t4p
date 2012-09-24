package test.dao.radioprogram;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import sg.edu.nus.iss.t4p.phoenix.core.dao.DaoFactory;
import sg.edu.nus.iss.t4p.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.t4p.phoenix.entity.radioprogram.*;
import sg.edu.nus.iss.t4p.phoenix.entity.user.User;


public class RadioProgramDaoTest {

	//@Test
	public void testRetrieveRadioProgramById(){
		Long radioprogramId = 1L;
		RadioProgram radioprogram = null;
		try {
			radioprogram = DaoFactory.getInstance().getRadioProgramDao().getObject(String.valueOf(radioprogramId));
			System.err.println(radioprogram.getProgramName());
			Assert.assertTrue(radioprogram.getId() != null);
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(radioprogram.getId() != null);
	}
	
	@Test
	public void testRetrieveRadioProgramByObject(){
		Long radioprogramId = 1L;
		RadioProgram radioprogram = null;
		try {
			radioprogram = new RadioProgram();
			radioprogram.setId(radioprogramId);
			radioprogram.setProgramName("Hello Singapore");
			radioprogram.setProgramDesc("Hello Singapore!!!");
			
			DaoFactory.getInstance().getRadioProgramDao().load(radioprogram);
			System.err.println(radioprogram.getProgramName());
			Assert.assertTrue(radioprogram.getProgramName().equals("Hello Singapore"));
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(radioprogram.getProgramName() != null);
	}
	
	//@Test
	public void testRetrieveCorrectRadioProgram(){
		String radioprogramname = "The Living Room";
		RadioProgram radioprogram = DaoFactory.getInstance().getRadioProgramDao().getByProgramName(radioprogramname);
		Assert.assertTrue(radioprogramname.equals(radioprogram.getProgramName()));
	}
	
	@Test
	public void testInsertRadioProgram(){
		RadioProgram radioprogram = new RadioProgram();
		try {
			
			radioprogram.setProgramName("Hello Singapore");
			radioprogram.setProgramDesc("Hello Singapore!!!!");
			radioprogram.setTypicalDuration("21:20:00");
			try {
				DaoFactory.getInstance().getRadioProgramDao().persist(radioprogram);
			} catch (NotFoundException e) {
				e.printStackTrace();
			}
			System.err.println(radioprogram.getProgramName());
			Assert.assertTrue(radioprogram.getId() != null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
