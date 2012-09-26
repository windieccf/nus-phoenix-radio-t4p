package test.service.schedule;

import junit.framework.Assert;

import org.junit.Test;


import java.util.Calendar;

import sg.edu.nus.iss.t4p.phoenix.core.exceptions.BusinessLogicException;
import sg.edu.nus.iss.t4p.phoenix.entity.scalar.MonthlySchedule;
import sg.edu.nus.iss.t4p.phoenix.entity.scalar.WeeklySchedule;


import sg.edu.nus.iss.t4p.phoenix.delegate.schedule.ScheduleDelegate;



public class SchedualServiceTest {

	//@Test
	public void testGetMonthlySlotByDate() {
		MonthlySchedule ms = null;
		
		Calendar cal = Calendar.getInstance();
		cal.set(2010, 1, 1);
		
		try{
			ms = ScheduleDelegate.getInstance().getMonthlySlotByDate(cal.getTime());
			
			Assert.assertNotNull(ms);
			
		} catch(BusinessLogicException e) {
			e.printStackTrace();
			Assert.fail();
		}
		
		Assert.assertNotNull(ms);
	}
	
	//@Test
	public void testGetWeeklySlotByDate() {
		WeeklySchedule ws = null;
		
		Calendar cal = Calendar.getInstance();
		cal.set(2010, 1, 1);
		
		try{
			ws = ScheduleDelegate.getInstance().getWeeklySlotByDate(cal.getTime());
			
			Assert.assertNotNull(ws);
			
		}catch(BusinessLogicException e)
		{
			e.printStackTrace();
			Assert.fail();
		}
		
		Assert.assertNotNull(ws);
	}
	
	@Test
	public void testSaveWeeklySlot() {
		WeeklySchedule ws = null;
		
		Calendar cal = Calendar.getInstance();
		cal.set(2010, 1, 1);
		
		try{
			ws = ScheduleDelegate.getInstance().getWeeklySlotByDate(cal.getTime());
			
			Assert.assertNotNull(ws);
			
		}catch(BusinessLogicException e)
		{
			e.printStackTrace();
			Assert.fail();
		}
		
		Assert.assertNotNull(ws);
		
		try{
			ScheduleDelegate.getInstance().saveWeeklySlot(ws);
		}catch(BusinessLogicException e)
		{
			e.printStackTrace();
			Assert.fail();
		}
	}
}


