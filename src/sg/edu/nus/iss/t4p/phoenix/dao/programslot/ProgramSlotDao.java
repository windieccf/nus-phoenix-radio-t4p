package sg.edu.nus.iss.t4p.phoenix.dao.programslot;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import sg.edu.nus.iss.t4p.phoenix.core.dao.BaseDaoIntf;
import sg.edu.nus.iss.t4p.phoenix.entity.scalar.WeeklySchedule;
import sg.edu.nus.iss.t4p.phoenix.entity.schedule.ProgramSlot;

public interface ProgramSlotDao extends BaseDaoIntf<ProgramSlot>{
	
	public int[] getMonthlyProgramCountByWeek(Date date) throws SQLException;
	public List<ProgramSlot> getProgramSlotByDateRange(Date dateFrom , Date dateTo) throws SQLException;
	public boolean isSlotTimeTaken(WeeklySchedule weeklySchedule) throws SQLException;

}
