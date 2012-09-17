package sg.edu.nus.iss.t4p.phoenix.entity.schedule;

import java.util.Date;

import sg.edu.nus.iss.t4p.phoenix.core.annotation.Column;
import sg.edu.nus.iss.t4p.phoenix.core.annotation.Column.TYPE;
import sg.edu.nus.iss.t4p.phoenix.core.annotation.Table;
import sg.edu.nus.iss.t4p.phoenix.core.entity.BaseEntity;
import sg.edu.nus.iss.t4p.phoenix.entity.user.User;


@SuppressWarnings("serial")
@Table(name="PROGRAM_SLOT")
public class ProgramSlot extends BaseEntity{

	public ProgramSlot(){
		this.presenter = new User();
		this.producer = new User();
	}
	
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
	
	
	@Column(name="PRESENTER_ID",columnType=TYPE.ORDINARY)
	private Long presenterId;
	public Long getPresenterId() {return presenterId;}
	public void setPresenterId(Long presenterId) {this.presenterId = presenterId;}
	
	@Column(name="PRODUCER_ID",columnType=TYPE.ORDINARY)
	private Long producerId;
	public Long getProducerId() {return producerId;}
	public void setProducerId(Long producerId) {this.producerId = producerId;}
	
	/** UTILITY FOR ORM ****************************************************************************************/
	private User presenter;
	public User getPresenter() {return presenter;}
	public void setPresenter(User presenter) {
		this.presenter = presenter;
		this.setPresenterId((this.presenter == null) ? null : this.presenter.getId());
	}

	private User producer;
	public User getProducer() {return producer;}
	public void setProducer(User producer) {
		this.producer = producer;
		this.setProducerId((this.producer == null) ? null : this.producer.getId());
	}
	
	
	
	
}
