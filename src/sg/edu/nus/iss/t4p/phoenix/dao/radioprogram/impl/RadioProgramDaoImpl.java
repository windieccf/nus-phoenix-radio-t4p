package sg.edu.nus.iss.t4p.phoenix.dao.radioprogram.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import sg.edu.nus.iss.t4p.phoenix.core.dao.impl.BaseDao;
import sg.edu.nus.iss.t4p.phoenix.dao.radioprogram.RadioProgramDao;
import sg.edu.nus.iss.t4p.phoenix.entity.radioprogram.RadioProgram;

public class RadioProgramDaoImpl extends BaseDao<RadioProgram> implements RadioProgramDao{
	
	private static RadioProgramDaoImpl instance;
	private RadioProgramDaoImpl(){
		/*SINGLETON*/
		super();
	}
	
	public static RadioProgramDaoImpl getInstance(){
		if(instance == null)
			instance = new RadioProgramDaoImpl();
		
		return instance;
	}
	
	@Override
	public ArrayList<RadioProgram> retrieveProgramList(){
		ArrayList<RadioProgram> programList = new ArrayList<RadioProgram>();
		try( Connection con = super.getConnection() ){
			StringBuffer mySql = new StringBuffer("SELECT * FROM ")
								.append( super.getTableName(RadioProgram.class.getName()));

			PreparedStatement stmt = con.prepareStatement(mySql.toString());
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				RadioProgram program = super.createValueObject();
				super.assignValue(program, program.getColumnField(), rs);
				programList.add(program);
			}
			
			return programList;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public RadioProgram getByProgramName(String programName){
		try( Connection con = super.getConnection() ){
			StringBuffer mySql = new StringBuffer("SELECT * FROM ")
								.append( super.getTableName(RadioProgram.class.getName()) + " WHERE UPPER(PROGRAM_NAME) = ? ");

			PreparedStatement stmt = con.prepareStatement(mySql.toString());
			stmt.setString(1, programName.toUpperCase());

			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				RadioProgram program = super.createValueObject();
				super.assignValue(program, program.getColumnField(), rs);
				return program;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	


}
