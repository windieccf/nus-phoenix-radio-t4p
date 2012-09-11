package sg.edu.nus.iss.t4p.phoenix.core.entity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sg.edu.nus.iss.t4p.phoenix.core.annotation.Column;
import sg.edu.nus.iss.t4p.phoenix.core.annotation.Column.TYPE;
import sg.edu.nus.iss.t4p.phoenix.core.annotation.PrePersist;
import sg.edu.nus.iss.t4p.phoenix.core.annotation.PreUpdate;
import sg.edu.nus.iss.t4p.phoenix.core.constant.ConstantStatus;

@SuppressWarnings("serial")
public abstract class BaseEntity implements Cloneable, Serializable{
	
	public boolean isPkSet(){
		List<Field> fields = this.getFieldsByType(Column.TYPE.PRIMARY);
		for(Field field : fields){
			try {
				field.setAccessible(true);
				Object val = field.get(this);
				if(val == null)
					return false;
				
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	public List<Field> getFieldsByType(Column.TYPE columnType){
		List<Field> fields = new ArrayList<Field>();

		Field fieldArray[] = this.getClass().getDeclaredFields();
		for(Field field : fieldArray){
			if(field.getAnnotation(Column.class)!=null){
				if(columnType.equals(field.getAnnotation(Column.class).columnType())){
					fields.add(field);
				}
				
			}
		}
		return fields;
	}
	
	public List<Field> getColumnField(){
		List<Field> fields = new ArrayList<Field>();
		Class<?> klazz = this.getClass(); 
		while(true){
			Field fieldArray[] = klazz.getDeclaredFields();
			for(Field field : fieldArray){
				if(field.getClass().getModifiers() == Modifier.FINAL || field.getClass().getModifiers() == Modifier.STATIC)
					continue;
				
				if(field.getAnnotation(Column.class)!=null){
					fields.add(field);
				}
			}
			if(klazz.getSimpleName().endsWith("BaseEntity"))
				break;

			klazz = klazz.getSuperclass();
		}
		
		
		
		return fields;
	}
	
	
	/*************************************** CALL BACK METHOD UPON PERSIST *********************************************************************/
	
	@PrePersist
	public void prePersist(){
		if(!this.isPkSet()){
			this.setStatus(ConstantStatus.ACTIVE);
			this.setCreatedDateTime(new Date());
		}
	}
	
	@PreUpdate
	public void preUpdate(){
		this.setModifiedDateTime(new Date());
	}
	
	
	/************************************ SHARED FIELD ********************************************************/
	@Column(name="STATUS",columnType=TYPE.ORDINARY)
	private String status;
	public String getStatus() {return status;}
	public void setStatus(String status) {this.status = status;}
	
	@Column(name="CREATED_DATETIME",columnType=TYPE.ORDINARY)
	private Date createdDateTime;
	public Date getCreatedDateTime() {return createdDateTime;}
	public void setCreatedDateTime(Date createdDateTime) {this.createdDateTime = createdDateTime;}
	
	@Column(name="MODIFIED_DATETIME",columnType=TYPE.ORDINARY)
	private Date modifiedDateTime;
	public Date getModifiedDateTime() {return modifiedDateTime;}
	public void setModifiedDateTime(Date modifiedDateTime) {this.modifiedDateTime = modifiedDateTime;}

	@Column(name="CREATED_BY_ID",columnType=TYPE.ORDINARY)
	private Long createdById;
	public Long getCreatedById() {return createdById;}
	public void setCreatedById(Long createdById) {this.createdById = createdById;}
	
	@Column(name="MODIFIED_BY_ID",columnType=TYPE.ORDINARY)
	private Long modifiedById;
	public Long getModifiedById() {return modifiedById;}
	public void setModifiedById(Long modifiedById) {this.modifiedById = modifiedById;}
	
}
