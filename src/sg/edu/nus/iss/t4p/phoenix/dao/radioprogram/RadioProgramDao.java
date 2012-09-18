package sg.edu.nus.iss.t4p.phoenix.dao.radioprogram;


import java.util.ArrayList;

import sg.edu.nus.iss.t4p.phoenix.core.dao.BaseDaoIntf;
import sg.edu.nus.iss.t4p.phoenix.entity.radioprogram.RadioProgram;

public interface RadioProgramDao extends BaseDaoIntf<RadioProgram>{
	
	/**
	 * retrieveProgramList-method. This will retrieve all contents from database table and build
	 * a List containing RadioProgram object. Please note, that this method will
	 * consume huge amounts of resources if table has lot's of rows. This should
	 * only be used when target tables have only small amounts of data.
	 * 
	 * @param conn
	 *            This method requires working database connection.
	 */
	public ArrayList<RadioProgram> retrieveProgramList();
	
	/**
	 * getByProgramName-method. This will get one or zero RadioProgram object from database,
	 * which based on passed in programName.
	 *
	 * @param conn
	 *            This method requires working database connection.
	 * @parm programName
	 *            This parameter used for retrieving RadioProgram Object.
	 *            It must be individual.             
	 */
	public RadioProgram getByProgramName(String programName);

}
