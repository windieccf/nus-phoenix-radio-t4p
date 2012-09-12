package sg.edu.nus.iss.t4p.phoenix.entity.schedule;

import java.util.Date;

import sg.edu.nus.iss.t4p.phoenix.core.annotation.Column;
import sg.edu.nus.iss.t4p.phoenix.core.annotation.Column.TYPE;
import sg.edu.nus.iss.t4p.phoenix.core.annotation.Table;
import sg.edu.nus.iss.t4p.phoenix.core.entity.BaseEntity;


@SuppressWarnings("serial")
@Table(name="PROGRAM_SLOT")
public class ProgramSlot extends BaseEntity{

	public ProgramSlot(){}
	
	@Column(name="ID",columnType=TYPE.PRIMARY)
	private Long id;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	
	@Column(name="START_DATETIME",columnType=TYPE.ORDINARY)
	private Date startDateTime;
	public Date getStartDateTime() {return startDateTime;}
	public void setStartDateTime(Date startDateTime) {this.startDateTime = startDateTime;}
	
	@Column(name="END_DATETIME",columnType=TYPE.ORDINARY)
	private Date endDateTime;
	public Date getEndDateTime() {return endDateTime;}
	public void setEndDateTime(Date endDateTime) {this.endDateTime = endDateTime;}
	
	
}
