package sg.edu.nus.iss.t4p.phoenix.dao.programslot.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import sg.edu.nus.iss.t4p.phoenix.core.dao.impl.BaseDao;
import sg.edu.nus.iss.t4p.phoenix.dao.programslot.ProgramSlotDao;
import sg.edu.nus.iss.t4p.phoenix.entity.scalar.WeeklySchedule;
import sg.edu.nus.iss.t4p.phoenix.entity.schedule.ProgramSlot;
import sg.edu.nus.iss.t4p.phoenix.utility.T4DateUtil;
import sg.edu.nus.iss.t4p.phoenix.utility.T4StringUtil;

public class ProgramSlotDaoImpl extends BaseDao<ProgramSlot> implements ProgramSlotDao{
	
	private static ProgramSlotDaoImpl instance;
	private ProgramSlotDaoImpl(){/*SINGLETON*/}
	public static ProgramSlotDaoImpl getInstance(){
		if(instance == null)
			instance = new ProgramSlotDaoImpl();
		
		return instance;
	}
	@Override
	public int[] getMonthlyProgramCountByWeek(Date date) throws SQLException {
		Calendar calFrom = T4DateUtil.getCalendar(date);
		calFrom = T4DateUtil.getStartOfDay(calFrom);
		List<String> sqlList = new ArrayList<String>();
		int result[] = new int[7];
		for(int i = 0; i < 7; i++){
			String dateParse = "DATE( '"+T4DateUtil.DATE_01.format(calFrom.getTime()) + "' ) ";
			String mySql = "select "+dateParse+" AS DATE , count(1) AS COUNT from "+super.getTableName(ProgramSlot.class.getName()) + " WHERE DATE(START_DATETIME) = " + dateParse ;
			sqlList.add(mySql);
			calFrom.add(Calendar.DATE,1);
		}
		
		String query = T4StringUtil.join(sqlList, " UNION ");
		try(Connection con = super.getConnection();){
			Statement stmt =con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			int i = 0;
			while(rs.next()){
				result[i] = rs.getInt("COUNT");
				i++;
			}
			
			
		}catch(Exception e){
			throw e;
		}
		
		
		return result;
	}
	@Override
	public List<ProgramSlot> getProgramSlotByDateRange(Date dateFrom,
			Date dateTo) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isSlotTimeTaken(WeeklySchedule weeklySchedule)
			throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
