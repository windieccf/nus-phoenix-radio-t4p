/*
 * CONFIDENTIAL AND PROPRIETARY SOURCE CODE OF
 * Institute of Systems Science, National University of Singapore
 *
 * Copyright 2012 Team 4(Part-Time), ISS, NUS, Singapore. All rights reserved.
 * Use of this source code is subjected to the terms of the applicable license
 * agreement.
 *
 * -----------------------------------------------------------------
 * REVISION HISTORY
 * -----------------------------------------------------------------
 * DATE             AUTHOR          REVISION		DESCRIPTION
 * 15 Sep 2012    	Team 4		    0.1				Initial creating
 * 													
 * 													
 * 
 */
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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sg.edu.nus.iss.t4p.phoenix.core.annotation.Column;
import sg.edu.nus.iss.t4p.phoenix.core.annotation.Table;
import sg.edu.nus.iss.t4p.phoenix.core.constant.ConstantStatus;
import sg.edu.nus.iss.t4p.phoenix.core.dao.DBConstants;
import sg.edu.nus.iss.t4p.phoenix.core.entity.BaseEntity;
import sg.edu.nus.iss.t4p.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.t4p.phoenix.utility.T4StringUtil;

/**
* Implementation for BaseDaoIntf
* @author Robin Foe A0092657U
* @version 1.0
* @see sg.edu.nus.iss.t4p.phoenix.core.dao.BaseDaoIntf
*/
public abstract class BaseDao<T extends BaseEntity> {

	private Class<T> klass;
	private static Map<String,String> TABLE_NAME = new HashMap<String,String>();
	
    /**
     * Base DAO constructor. 
     */
	@SuppressWarnings("unchecked")
	public BaseDao(){
		// retrieving the generic type of the class
		Type type = (getClass().getGenericSuperclass() instanceof ParameterizedType) ? getClass().getGenericSuperclass() : getClass().getSuperclass().getGenericSuperclass();
		ParameterizedType paramType = (ParameterizedType) type;
		klass = (Class<T>) paramType.getActualTypeArguments()[0];
		
		// initialize the table name;
		this.getTableName(klass.getName());
	}
	
	/**
	 * Create new instance for the object
	 * @return new stance or null
	 */
	public T createValueObject(){
		try {
			return klass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Get object through user ID
	 * @param id
	 * @return object
	 * @throws NotFoundException
	 * @throws SQLException
	 */
	public T getObject(String id) throws NotFoundException, SQLException{
		T valueObj = this.createValueObject();
		List<Field> fields = valueObj.getFieldsByType(Column.TYPE.PRIMARY);
		
		StringBuffer mySql = new StringBuffer("SELECT * FROM ")
											.append( TABLE_NAME.get(this.klass.getName()) )
											.append(" WHERE 1=1 ");
		
		for(Field field : fields){
			String columnName = field.getAnnotation(Column.class).name();
			mySql.append(" AND "+columnName+" = ? ");
		}
		
		try(Connection con = this.getConnection();) {
			
			PreparedStatement stmt = con.prepareStatement(mySql.toString());
			stmt.setLong(1,Long.parseLong(id));
			ResultSet rs = stmt.executeQuery();
			fields = valueObj.getColumnField();
			
			if(rs.next())
				this.assignValue(valueObj, fields, rs);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return valueObj;
	}
	
	/**
	 * Load value object
	 * @param valueObject
	 * @throws NotFoundException
	 * @throws SQLException
	 */
	public void load(T valueObject)	throws NotFoundException, SQLException{
		List<Field> fields = valueObject.getFieldsByType(Column.TYPE.PRIMARY);
		try (Connection con = this.getConnection(); ) {
			StringBuffer mySql = new StringBuffer("SELECT * FROM ")
												.append( TABLE_NAME.get(this.klass.getName()) )
												.append(" WHERE 1=1 ");
			
			Long pk = null;
			for(Field field : fields){
				String columnName = field.getAnnotation(Column.class).name();
				mySql.append(" AND "+columnName+" = ? ");
				
				if(field.get(valueObject) == null)
					throw new IllegalArgumentException("BaseEntity.load Pk is not set");
				
				pk = field.getLong(valueObject);
			}
			
			PreparedStatement stmt = con.prepareStatement(mySql.toString());
			stmt.setLong(1,pk);
			ResultSet rs = stmt.executeQuery();
			fields = valueObject.getColumnField();
			if(rs.next())
				this.assignValue(valueObject, fields, rs);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * List all items according to object
	 * @return list of objects
	 * @throws SQLException
	 */
	public List<T> loadAll() throws SQLException{
		List<T> list = new ArrayList<T>();
		StringBuffer mySql = new StringBuffer("SELECT * FROM ")
							.append( TABLE_NAME.get(this.klass.getName()) );
		
		try (Connection con = this.getConnection(); ) {
			ResultSet rs = con.createStatement().executeQuery(mySql.toString());
			T valueObject = this.createValueObject();
			List<Field> fields= valueObject.getColumnField();
			
			while(rs.next()){
				valueObject = this.createValueObject();
				this.assignValue(valueObject, fields, rs);
				list.add(valueObject);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * If object exist @see BaseDao#merge(BaseEntity)
	 * If object not existing, a new object will be created
	 * @param valueObject
	 * @throws NotFoundException
	 * @throws SQLException
	 */
	public void persist(T valueObject)throws NotFoundException, SQLException{
		if(valueObject.isPkSet())
			this.merge(valueObject);
		else{
			StringBuffer mySql = new StringBuffer(" INSERT INTO " + this.getTableName(klass.getName()) );

			List<Field> fields = valueObject.getColumnField();
			
			List<String> columns = new ArrayList<String>();
			List<Object> params = new ArrayList<Object>();
			
			for(Field field : fields){
				Column col = field.getAnnotation(Column.class);
				String columnName = col.name();
				Object val = null;
				try {
					field.setAccessible(true);
					val = field.get(valueObject);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
				
				// upon insert, we will neglect the PK
				if(col.columnType().compareTo(Column.TYPE.PRIMARY) == 0 || val == null)
					continue;
				
				columns.add(columnName);
				params.add(val);
			}
			mySql.append(" (  "+ T4StringUtil.join(columns, " , ") +" ) VALUES ( " +this.constructParameter(columns.size())+" ) " ) ;
			System.err.println(mySql.toString());
			
			try(Connection con = this.getConnection()){
				PreparedStatement stmt = con.prepareStatement(mySql.toString());
				for(int i = 1; i <= params.size(); i++){
					stmt.setObject(i, params.get(i-1));
				}
				stmt.executeUpdate();
			}catch(SQLException e){
				throw e;
			}
			
			
		}
	}
	
	/**
	 * Merge the existing object based on object ID
	 * @param valueObject
	 * @throws NotFoundException
	 * @throws SQLException
	 */
	public void merge(T valueObject)throws NotFoundException, SQLException{
		
		StringBuffer mySql = new StringBuffer(" UPDATE " + this.getTableName(klass.getName()) + " SET ");

		List<Field> fields = valueObject.getColumnField();
		List<String> columnSet = new ArrayList<String>();
		List<Object> params = new ArrayList<Object>();
		Object pk = null;
		for(Field field : fields){
			Column col = field.getAnnotation(Column.class);
			String columnName = col.name();
			Object val = null;
			try {
				field.setAccessible(true);
				val = field.get(valueObject);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
			
			if(col.columnType().compareTo(Column.TYPE.PRIMARY) == 0)
				pk = val;
			
			// upon update, we will neglect the PK
			if(col.columnType().compareTo(Column.TYPE.PRIMARY) == 0 || val == null)
				continue;
			
			columnSet.add(columnName + " = ? ");
			params.add(val);
		}
		mySql.append( T4StringUtil.join(columnSet, " , "))
			.append(" WHERE ID = ? ");
		
		params.add(pk);
		
		try(Connection con = this.getConnection()){
			PreparedStatement stmt = con.prepareStatement(mySql.toString());
			for(int i = 1; i <= params.size(); i++){
				stmt.setObject(i, params.get(i-1));
			}
			stmt.executeUpdate();
		}catch(SQLException e){
			throw e;
		}
	}
	
	/**
	 * Soft delete the object
	 * @param valueObject
	 * @throws NotFoundException
	 * @throws SQLException
	 */
	public void remove(T valueObject)throws NotFoundException, SQLException{
		valueObject.setStatus(ConstantStatus.DELETE);
		this.merge(valueObject);
	}
	
	/**
	 * Remove all the object under the same class
	 * @throws SQLException
	 */
	public void removeAll() throws SQLException{
		StringBuffer mySql = new StringBuffer(" UPDATE " + this.getTableName(klass.getName()) + " SET STATUS = '"+ConstantStatus.DELETE+"' ");
		
		try(Connection con = this.getConnection()){
			PreparedStatement stmt = con.prepareStatement(mySql.toString());
			stmt.executeUpdate();
		}catch(SQLException e){
			throw e;
		}
		
	}
	
	/**
	 * Get total number of objects under the same class
	 * @return
	 * @throws SQLException
	 */
	public int countAll() throws SQLException{
		int result = 0;
		StringBuffer mySql = new StringBuffer(" SELECT COUNT(1) AS COUNTER FROM " + this.getTableName(klass.getName()));
		try(Connection con = this.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(mySql.toString());
			if(rs.next())
				result = rs.getInt("COUNTER");
			
		}catch(Exception e){
			throw e;
		}
		
		return result;
	}
	
	public List<T> searchMatching(T valueObject)throws SQLException{
		return null;
	}
	
	public  List<T> paginate(Long pageNo, Long rowPerPage, T valueObject) throws SQLException{
		
		List<T> list = new ArrayList<T>();
		StringBuffer mySql = new StringBuffer("SELECT * FROM ")
							.append( TABLE_NAME.get(this.klass.getName()) )
							.append(" WHERE 1=1  ");
		
		long startIdx = pageNo * rowPerPage;
		
		List<String> columns = new ArrayList<String>();
		List<Object> params = new ArrayList<Object>();
		
		
		if(valueObject!=null){
			List<Field> filterFields = valueObject.getColumnField();
			for(Field field : filterFields){
				Column col = field.getAnnotation(Column.class);
				String columnName = col.name();
				Object val = null;
				try {
					field.setAccessible(true);
					val = field.get(valueObject);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
				if(val!=null){
					columns.add(columnName + " = ? ");
					params.add(val);
				}
				
			}
			
		}
		
		if(!columns.isEmpty())
			mySql.append(" AND " + T4StringUtil.join(columns , " AND ") );
		
		mySql.append(" limit "+startIdx+" , " + rowPerPage);
//		System.err.println(" SQL " + mySql.toString() );
		
		try (Connection con = this.getConnection(); ) {
			PreparedStatement stmt = con.prepareStatement(mySql.toString());
			int i = 1;
			for(Object obj : params){
				stmt.setObject(i++, obj);
			}
			
			
			ResultSet rs = stmt.executeQuery();
			T valObj = this.createValueObject();
			List<Field> fields= valObj.getColumnField();
			
			while(rs.next()){
				valObj = this.createValueObject();
				this.assignValue(valObj, fields, rs);
				list.add(valObj);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	/**
	 * Get JDBC connection
	 * @return
	 */
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
				 else if (dataType.equals(String.class))
					 field.set(valueObj, rs.getString(columnName));
				 else{
					 /*IGNORED*/
				 }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	protected String getTableName(String klassName){
		
		if(!TABLE_NAME.containsKey(klassName)){
			try{
				Class<? extends BaseEntity> klazz = (Class<? extends BaseEntity>) Class.forName(klassName);
				
				if(klazz.getAnnotation(Table.class) == null)
					throw new IllegalArgumentException("The Entity does not contain @Table annotation");
				
				String schema = klazz.getAnnotation(Table.class).schema();
				String tableName = klazz.getAnnotation(Table.class).name();
				
				if( tableName == null || "".equals( tableName.trim() ))
					throw new IllegalArgumentException("The @Table annotation must contain Name");
				
				tableName = (schema == null || "".equals( schema.trim() )) ? tableName : schema + "." + tableName;
				TABLE_NAME.put(klassName, tableName);
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return TABLE_NAME.get(klassName);
	}

	private String constructParameter(int columnCount){
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < columnCount; i++){
			sb.append("?");
			if(i != columnCount -1 )
    			sb.append(",");
			
		}
		return sb.toString();
	}
	
}
