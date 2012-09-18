package sg.edu.nus.iss.t4p.phoenix.service.radioprogram;

import java.sql.SQLException;
import java.util.List;

import sg.edu.nus.iss.t4p.phoenix.core.dao.DaoFactory;
import sg.edu.nus.iss.t4p.phoenix.entity.radioprogram.RadioProgram;


public class RadioProgramService {
	
	/** 
     * Constructor. 
     * It takes no arguments and provides the most simple
     * way to create object instance. 
     */
	public static RadioProgramService instance;
	private RadioProgramService(){
		/*SINGELTON*/
	}
	
	/**
	 * getInstance-method. This method is used to create a new RadioProgramService
	 * object if it does not exists. 
	 */
	public static RadioProgramService getInstance(){
		if(instance == null)
			instance = new RadioProgramService();
		
		return instance;
	}
		
	/**
	 * retrieveProgramList-method. This will retrieve a list of RadioProgram object.
	 * Please note, that this method will consume huge amounts of resources if table has lot's of rows.
	 * This should only be used when target tables have only small amounts of data.
	 */
	public List<RadioProgram> retrieveProgramList()  {
		List<RadioProgram> programList = null;
		try {
			programList = DaoFactory.getInstance().getRadioProgramDao().loadAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return programList;
	}
	
	/**
	 * getByProgramName-method. This will get one or zero RadioProgram object based on passing in programName.
	 *
	 * @parm programName
	 *            This parameter used for retrieving RadioProgram Object.
	 *            It must be individual.             
	 */
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
