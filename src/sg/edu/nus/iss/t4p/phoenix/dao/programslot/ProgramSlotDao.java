package sg.edu.nus.iss.t4p.phoenix.dao.programslot;

import java.sql.SQLException;
import java.util.Date;

import sg.edu.nus.iss.t4p.phoenix.core.dao.BaseDaoIntf;
import sg.edu.nus.iss.t4p.phoenix.entity.schedule.ProgramSlot;

public interface ProgramSlotDao extends BaseDaoIntf<ProgramSlot>{
	
	public int[] getMonthlyProgramCountByWeek(Date date) throws SQLException;

}
