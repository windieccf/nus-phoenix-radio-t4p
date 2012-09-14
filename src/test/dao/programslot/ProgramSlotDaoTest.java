package test.dao.programslot;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import junit.framework.Assert;

import org.junit.Test;

import sg.edu.nus.iss.t4p.phoenix.core.dao.DaoFactory;
import sg.edu.nus.iss.t4p.phoenix.dao.programslot.ProgramSlotDao;

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

}
