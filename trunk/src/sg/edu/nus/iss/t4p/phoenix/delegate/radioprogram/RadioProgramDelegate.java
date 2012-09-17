package sg.edu.nus.iss.t4p.phoenix.delegate.radioprogram;

import java.util.List;

import sg.edu.nus.iss.t4p.phoenix.entity.radioprogram.RadioProgram;
import sg.edu.nus.iss.t4p.phoenix.service.radioprogram.RadioProgramService;

public class RadioProgramDelegate {
	public static RadioProgramDelegate instance;
	private RadioProgramDelegate(){
		/*SINGLETON*/
	}
	
	public static RadioProgramDelegate getInstance(){
		if(instance == null)
			instance = new RadioProgramDelegate();
		
		return instance;
	}
	
	public List<RadioProgram> retrieveProgramList() {
		return (RadioProgramService.getInstance().retrieveProgramList());
	}
	
	public RadioProgram getByProgramName(String programName) {
		return (RadioProgramService.getInstance().getByProgramName(programName));
	}
}
