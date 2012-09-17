package sg.edu.nus.iss.t4p.phoenix.service.radioprogram;

import java.sql.SQLException;
import java.util.List;

import sg.edu.nus.iss.t4p.phoenix.core.dao.DaoFactory;
import sg.edu.nus.iss.t4p.phoenix.entity.radioprogram.RadioProgram;


public class RadioProgramService {
	public static RadioProgramService instance;
	private RadioProgramService(){
		/*SINGELTON*/
	}
	
	public static RadioProgramService getInstance(){
		if(instance == null)
			instance = new RadioProgramService();
		
		return instance;
	}
	
	public List<RadioProgram> retrieveProgramList()  {
		List<RadioProgram> programList = null;
		try {
			programList = DaoFactory.getInstance().getRadioProgramDao().loadAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return programList;
	}
	
	public RadioProgram getByProgramName(String programName) {
		RadioProgram program = null;
		try {
			program = DaoFactory.getInstance().getRadioProgramDao().getByProgramName(programName);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return program;
	}
}
