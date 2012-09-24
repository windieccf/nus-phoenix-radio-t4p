package test.dao.programslot;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import sg.edu.nus.iss.t4p.phoenix.core.dao.DaoFactory;
import sg.edu.nus.iss.t4p.phoenix.dao.programslot.ProgramSlotDao;
import sg.edu.nus.iss.t4p.phoenix.entity.schedule.ProgramSlot;

public class ProgramSlotDaoTest {
	
	@Test
	public void testMonthlyCountByWeek(){
		ProgramSlotDao programSlotDao = DaoFactory.getInstance().getProgramSlotDao();
		Calendar cal = new GregorianCalendar();
		try {
			int[] result = programSlotDao.getMonthlyProgramCountByWeek(cal.getTime());
			Assert.assertEquals(7, result.length);
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail();
		}
		
	}
	
	@Test
	public void testProgramSlotByDateRange(){
		ProgramSlotDao programSlotDao = DaoFactory.getInstance().getProgramSlotDao();
		
		Calendar calFrom = Calendar.getInstance();
		calFrom.set(2010, 1, 1);
		
		Calendar calTo= Calendar.getInstance();
		calTo.set(2010, 12, 1);
		
		try{
			List<ProgramSlot> programSlotList = programSlotDao.getProgramSlotByDateRange(calFrom.getTime(), calTo.getTime());
			Assert.assertNotNull(programSlotList);
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	//@Test 
	public void testSlotTimeTaken(){
		
	}
	
	//@Test
	public void testPersist(){
		
	}
}





















