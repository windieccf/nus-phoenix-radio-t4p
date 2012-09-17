package sg.edu.nus.iss.t4p.phoenix.dao.programslot.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
import sg.edu.nus.iss.t4p.phoenix.entity.user.User;
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
	public List<ProgramSlot> getProgramSlotByDateRange(Date dateFrom, Date dateTo) throws SQLException {
		Calendar calFrom = T4DateUtil.getCalendar(dateFrom);
		calFrom = T4DateUtil.getStartOfDay(calFrom);
		
		Calendar calTo  = T4DateUtil.getCalendar(dateTo);
		calTo = T4DateUtil.getEndOfDay(calTo);
		
		String dateFromParse = "DATE( '"+T4DateUtil.DATE_01.format(calFrom.getTime()) + "' ) ";
		String dateToParse = "DATE( '"+T4DateUtil.DATE_01.format(calTo.getTime()) + "' ) ";
		
		List<ProgramSlot> programSlots= new ArrayList<ProgramSlot>();
		
		try(Connection con = super.getConnection()){
			StringBuffer mySql = new StringBuffer("SELECT P.ID AS SLOT_ID, P.START_DATETIME AS START_DATETIME, P.END_DATETIME AS END_DATETIME,  " )
										.append(" UPR.ID AS PRESENTER_ID, UPR.FIRST_NAME AS PRESENTER_NAME , UPO.ID AS PRODUCER_ID, UPO.LAST_NAME AS PRODUCER_NAME ")
										.append(" FROM " + super.getTableName(ProgramSlot.class.getName())+ " P  " )
										.append(" LEFT OUTER JOIN " + super.getTableName(User.class.getName())+ " UPR  ON UPR.ID = P.PRESENTER_ID ")
										.append(" LEFT OUTER JOIN " + super.getTableName(User.class.getName())+ " UPO  ON UPO.ID = P.PRODUCER_ID ")
										.append(" WHERE 1=1 ")
										.append(" AND DATE(P.START_DATETIME) >= " + dateFromParse )
										.append(" AND DATE(P.END_DATETIME) <= " + dateToParse)
										.append(" ORDER BY P.START_DATETIME ASC ");
			
			PreparedStatement stmt = con.prepareStatement(mySql.toString());
			
			
			
			
//			stmt.setDate(1, T4DateUtil.getSqlDate(calFrom));
//			stmt.setDate(2, T4DateUtil.getSqlDate(calTo));
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				ProgramSlot programSlot = super.createValueObject();
				programSlot.setId(rs.getLong("SLOT_ID"));
				programSlot.setStartDateTime(rs.getDate("START_DATETIME"));
				programSlot.setEndDateTime(rs.getDate("END_DATETIME"));
				programSlot.setPresenterId(rs.getLong("PRESENTER_ID"));
				programSlot.setProducerId(rs.getLong("PRODUCER_ID"));
				programSlot.getPresenter().setFirstName(rs.getString("PRESENTER_NAME"));
				programSlot.getProducer().setFirstName(rs.getString("PRODUCER_NAME"));
				
				programSlots.add(programSlot);
			}
			
		}catch(Exception e){
			throw e;
		}
		
		return programSlots;
	}
	@Override
	public boolean isSlotTimeTaken(WeeklySchedule weeklySchedule)
			throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
