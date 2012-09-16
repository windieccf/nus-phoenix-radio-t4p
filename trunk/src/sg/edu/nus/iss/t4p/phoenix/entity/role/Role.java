package sg.edu.nus.iss.t4p.phoenix.entity.role;

import sg.edu.nus.iss.t4p.phoenix.core.annotation.Column;
import sg.edu.nus.iss.t4p.phoenix.core.annotation.Column.TYPE;
import sg.edu.nus.iss.t4p.phoenix.core.annotation.Table;
import sg.edu.nus.iss.t4p.phoenix.core.entity.BaseEntity;

@SuppressWarnings("serial")
@Table(name="ROLE")
public class Role extends BaseEntity{
	public Role(){}
	
	@Column(name="ID",columnType=TYPE.PRIMARY)
	private Long id;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}

	@Column(name="ROLE",columnType=TYPE.ORDINARY)
	private String role;
	public String getRole() {return role;}
	public void setRole(String role) {this.role = role;}
	
	@Column(name="ACCESS_PRIVILEDGE",columnType=TYPE.ORDINARY)
	private String accesspriviledge;
	public String getAccesspriviledge() {return accesspriviledge;}
	public void setAccesspriviledge(String accesspriviledge) {this.accesspriviledge = accesspriviledge;}
	
	
}
