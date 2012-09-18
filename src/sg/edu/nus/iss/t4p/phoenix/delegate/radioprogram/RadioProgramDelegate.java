package sg.edu.nus.iss.t4p.phoenix.delegate.radioprogram;

import java.util.List;

import sg.edu.nus.iss.t4p.phoenix.entity.radioprogram.RadioProgram;
import sg.edu.nus.iss.t4p.phoenix.service.radioprogram.RadioProgramService;

public class RadioProgramDelegate {
	
	/** 
     * Constructor. 
     * It takes no arguments and provides the most simple
     * way to create object instance. 
     */
	public static RadioProgramDelegate instance;
	private RadioProgramDelegate(){
		/*SINGLETON*/
	}
	
	
	/**
	 * getInstance-method. This method is used to create a new RadioProgramDelegate
	 * object if it does not exists. 
	 */
	public static RadioProgramDelegate getInstance(){
		if(instance == null)
			instance = new RadioProgramDelegate();
		
		return instance;
	}
	
	/**
	 * retrieveProgramList-method. This will retrieve a list of RadioProgram object.
	 * Please note, that this method will consume huge amounts of resources if table has lot's of rows.
	 * This should only be used when target tables have only small amounts of data.
	 */
	public List<RadioProgram> retrieveProgramList() {
		return (RadioProgramService.getInstance().retrieveProgramList());
	}
	
	
	/**
	 * getByProgramName-method. This will get one or zero RadioProgram object based on passing in programName.
	 *
	 * @parm programName
	 *            This parameter used for retrieving RadioProgram Object.
	 *            It must be individual.             
	 */
	public RadioProgram getByProgramName(String programName) {
		return (RadioProgramService.getInstance().getByProgramName(programName));
	}
}
