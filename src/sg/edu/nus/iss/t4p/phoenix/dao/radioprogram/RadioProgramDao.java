package sg.edu.nus.iss.t4p.phoenix.dao.radioprogram;


import java.util.ArrayList;

import sg.edu.nus.iss.t4p.phoenix.core.dao.BaseDaoIntf;
import sg.edu.nus.iss.t4p.phoenix.entity.radioprogram.RadioProgram;

public interface RadioProgramDao extends BaseDaoIntf<RadioProgram>{
	
	public ArrayList<RadioProgram> retrieveProgramList();
	public RadioProgram getByProgramName(String programName);

}
