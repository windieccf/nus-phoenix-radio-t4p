package sg.edu.nus.iss.t4p.phoenix.core.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sg.edu.nus.iss.t4p.phoenix.core.annotation.Column;
import sg.edu.nus.iss.t4p.phoenix.core.annotation.Table;
import sg.edu.nus.iss.t4p.phoenix.core.dao.DBConstants;
import sg.edu.nus.iss.t4p.phoenix.core.entity.BaseEntity;
import sg.edu.nus.iss.t4p.phoenix.core.exceptions.NotFoundException;

public abstract class BaseDao<T extends BaseEntity> {

	private Class<T> klass;
	private static Map<String,String> TABLE_NAME = new HashMap<String,String>();
	
	@SuppressWarnings("unchecked")
	public BaseDao(){
		// retrieving the generic type of the class
		Type type = (getClass().getGenericSuperclass() instanceof ParameterizedType) ? getClass().getGenericSuperclass() : getClass().getSuperclass().getGenericSuperclass();
		ParameterizedType paramType = (ParameterizedType) type;
		klass = (Class<T>) paramType.getActualTypeArguments()[0];
		
		// initialize the table name;
		if(!TABLE_NAME.containsKey(klass.getName())){
			if(klass.getAnnotation(Table.class) == null)
				throw new IllegalArgumentException("The Entity does not contain @Table annotation");
			
			String schema = klass.getAnnotation(Table.class).schema();
			String tableName = klass.getAnnotation(Table.class).name();
			
			if( tableName == null || "".equals( tableName.trim() ))
				throw new IllegalArgumentException("The @Table annotation must contain Name");
			
			tableName = (tableName == null || "".equals( tableName.trim())) ? tableName : schema + "." + tableName;
			TABLE_NAME.put(klass.getName(), tableName);
		}
	}
	
	public T createValueObject(){
		try {
			return klass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public T getObject(String id) throws NotFoundException, SQLException{
		T valueObj = this.createValueObject();
		List<Field> fields = valueObj.getFieldsByType(Column.TYPE.PRIMARY);
		
		StringBuffer mySql = new StringBuffer("SELECT * FROM ")
									.append( TABLE_NAME.get(this.klass.getName()) + " WHERE 1=1 ");
		
		for(Field field : fields){
			String columnName = field.getAnnotation(Column.class).name();
			mySql.append(" AND "+columnName+" = ? ");
		}
		
		Connection con = null;
		PreparedStatement stmt = null;
		try{
//			TODO 
			con = this.getConnection();
			//PreparedStatement stmt = con.prepareStatement(mySql.toString());
			stmt.setLong(1,Long.parseLong(id));
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				this.assignValue(valueObj, fields, rs);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		
		
		
		return valueObj;
	}
	
	public void load(T valueObject)	throws NotFoundException, SQLException{
		List<Field> fields = valueObject.getFieldsByType(Column.TYPE.PRIMARY);
		try {
			StringBuffer mySql = new StringBuffer("SELECT * FROM ")
									.append( TABLE_NAME.get(this.klass.getSimpleName()) + " WHERE 1=1 ");
			
			Long pk = null;
			
			for(Field field : fields){
				String columnName = field.getAnnotation(Column.class).name();
				mySql.append(" AND "+columnName+" = ? ");
				
				if(field.get(valueObject) == null)
					throw new IllegalArgumentException("BaseEntity.load Pk is not set");
				
				pk = field.getLong(valueObject);
			}
			
			Connection con = this.getConnection();
			PreparedStatement stmt = con.prepareStatement(mySql.toString());
			stmt.setLong(1,pk);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public List<T> loadAll() throws SQLException{
		return null;
	}
	
	
	public void persist(T valueObject)throws SQLException{
		
	}
	
	
	public void merge(T valueObject)throws NotFoundException, SQLException{
		
	}
	
	public void remove(T valueObject)throws NotFoundException, SQLException{}
	
	public void removeAll() throws SQLException{}
	
	public int countAll() throws SQLException{
		return 0;
	}
	
	public List<T> searchMatching(T valueObject)throws SQLException{
		return null;
	}
	
	
	protected Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(DBConstants.COM_MYSQL_JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection(DBConstants.dbUrl, DBConstants.dbUserName, DBConstants.dbPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	protected void assignValue(T valueObj, List<Field> fields, ResultSet rs) throws SQLException{
		for(Field field : fields){
			Class<?> dataType = field.getType();
			String columnName = field.getAnnotation(Column.class).name();
			Object val = rs.getObject(columnName);
			field.setAccessible(true);
			try {
				if(val == null)
					continue;
				
				 if(dataType.equals(Integer.class))
					 field.set(valueObj, rs.getInt(columnName));
				 else if (dataType.equals(Long.class))
					 field.set(valueObj, rs.getLong(columnName));
				 else if (dataType.equals(Double.class))
					 field.set(valueObj, rs.getDouble(columnName));
				 else if (dataType.equals(Float.class))
					 field.set(valueObj, rs.getFloat(columnName));
				 else if (dataType.equals(BigDecimal.class))
					 field.set(valueObj, rs.getBigDecimal(columnName));
				 else if (dataType.equals(Date.class))
					 field.set(valueObj, new Date(rs.getTimestamp(columnName).getTime() ));
				 else
					 field.set(valueObj, rs.getString(columnName));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	protected String getTableName(String klassName){
		return TABLE_NAME.get(klassName);
	}
	
}
