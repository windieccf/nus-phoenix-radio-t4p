package test.service.radioprogram;


import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import sg.edu.nus.iss.t4p.phoenix.entity.radioprogram.RadioProgram;
import sg.edu.nus.iss.t4p.phoenix.delegate.radioprogram.RadioProgramDelegate;

public class RadioProgramServiceTest {

	//@Test
	public void testRetrieveProgramList() {
		
		List<RadioProgram> radioprogramlist = null;
		
		radioprogramlist = RadioProgramDelegate.getInstance().retrieveProgramList();
		
		System.err.println(radioprogramlist.get(6).getProgramName());

		Assert.assertTrue(radioprogramlist!=null);		
	}
	
	@Test
	public void testGetByProgramName() {
		
		RadioProgram radioprogram = null;
		
		radioprogram = RadioProgramDelegate.getInstance().getByProgramName("They are Making a Di");
		
		System.err.println(radioprogram.getProgramName());
		
		Assert.assertTrue(radioprogram.getProgramName().equals("They are Making a Di"));	
	}

}
