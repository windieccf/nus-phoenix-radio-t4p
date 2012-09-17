package sg.edu.nus.iss.t4p.phoenix.entity.radioprogram;

import sg.edu.nus.iss.t4p.phoenix.core.annotation.Column;
import sg.edu.nus.iss.t4p.phoenix.core.annotation.Column.TYPE;
import sg.edu.nus.iss.t4p.phoenix.core.annotation.Table;
import sg.edu.nus.iss.t4p.phoenix.core.entity.BaseEntity;


@SuppressWarnings("serial")
@Table(name="RADIO_PROGRAM")
public class RadioProgram extends BaseEntity{

	public RadioProgram(){}
	
	@Column(name="ID",columnType=TYPE.PRIMARY)
	private Long id;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	
	@Column(name="PROGRAM_NAME",columnType=TYPE.ORDINARY)
	private String programName;
	public String getProgramID() {return programName;}
	public void setProgramID(String programName) {this.programName = programName;}
	
	@Column(name="PROGRAM_DESC",columnType=TYPE.ORDINARY)
	private String programDesc;
	public String getProgramDesc() {return programDesc;}
	public void setProgramDesc(String programDesc) {this.programDesc = programDesc;}
}
