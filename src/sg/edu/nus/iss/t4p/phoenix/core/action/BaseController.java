package sg.edu.nus.iss.t4p.phoenix.core.action;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.edu.nus.iss.t4p.phoenix.core.entity.BaseEntity;
import sg.edu.nus.iss.t4p.phoenix.utility.T4DateUtil;
import sg.edu.nus.iss.t4p.phoenix.utility.T4StringUtil;


/**
* Abstract class for controller, perform URL to method call conversion
* @author Robin Foe A0092657U
* @version 1.0
*/
@SuppressWarnings("serial")
public abstract class BaseController extends HttpServlet {
	
	public BaseController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponseresponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * This method process the request from url and convert to appropriate method
	 * @param request the HttpServletRequest
	 * @param response the HttpServletResponse
	 * @exception javax.servlet.ServletException
	 * @exception java.io.IOException
	 * @see javax.servlet.ServletException
	 * @see java.io.IOException
	 */
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	protected void processRequest(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{
		 String pathInfo = request.getPathInfo();
		 String methodName = T4StringUtil.translateUrlToMethod(pathInfo);
		 try{
			 Class klass = this.getClass();
			 Method mtd = klass.getDeclaredMethod(methodName,HttpServletRequest.class, HttpServletResponse.class);
			 mtd.setAccessible(true);
			 mtd.invoke(this, request,response);
			 
		 }catch(Exception e){
			 e.printStackTrace();
			 throw new ServletException(e.getMessage());
		 }
	 }
	 
	 
	 /**
	 * This method preform redirection
	 * @param request the HttpServletRequest
	 * @param response the HttpServletResponse
	 * @param path url path
	 * @exception java.io.IOException
	 * @see java.io.IOException
	 */
	 
	 protected void doRedirect(HttpServletRequest request,	HttpServletResponse response, String path) throws IOException{
		 response.sendRedirect(request.getContextPath()+"/"+path);
	 }
	 
	 /**
	 * short hand method for retrieveParameter, will use class simple name as the paramName
	 * @param request the HttpServletRequest
	 * @param klass the entity class
	 * @return T the object of the class type
	 */
	 protected <T> T retrieveParameter(HttpServletRequest request , Class<T> klass){
		 return this.retrieveParameter(request, klass, null);
	 }
	 
	 /**
	 * This method help to retrieve the request parameter and translate it into the entity object of the given class type
	 * @param request the HttpServletRequest
	 * @param klass the entity class
	 * @param paramName prefix of the request parameter name, if applicable.
	 * @return T the object of the class type
	 */
	 protected <T> T retrieveParameter(HttpServletRequest request , Class<T> klass, String paramName){
		 
		 try {
			 String prefix = T4StringUtil.decapitalize( (T4StringUtil.isEmpty(paramName) ) ? klass.getSimpleName(): paramName);
			 
			 Constructor<T> constructor = klass.getConstructor();
			 T t = constructor.newInstance();
			 
			 List<Field> fields = (t instanceof BaseEntity) ? ((BaseEntity)t).getAllField() : Arrays.asList(klass.getDeclaredFields());

			 for(Field field : fields){
				 if(field.getClass().getModifiers() == Modifier.FINAL || field.getClass().getModifiers() == Modifier.STATIC)
					continue;
				 
				 String fieldName = prefix + "." + field.getName();
				 String fieldTypeName = field.getType().getName();
				 
				 if("java.util.List".equals(fieldTypeName)){
					 // handling for listing item
					 Type type =  field.getGenericType();
					 ParameterizedType paramType = (ParameterizedType) type;
					 Class<?> listClass = (Class<?>) paramType.getActualTypeArguments()[0];
					 
					 List<?> paramList = this.retrieveListParam(request, listClass, fieldName);
					 field.setAccessible(true);
					 field.set(t, paramList);
				 }else if (fieldTypeName.startsWith("sg.edu.nus.iss.t4p.phoenix.entity")){
					 // handling for entity level
					 Object obj = this.retrieveParameter(request, Class.forName(fieldTypeName),fieldName);
					 if(obj!=null){
						 field.setAccessible(true);
						 field.set(t, obj);
					 }
				 }else{
					 String value = request.getParameter(fieldName);
					 if(!T4StringUtil.isEmpty(value)){
						 field.setAccessible(true);
						 field.set(t, this.valueConvert(t, field, value));
					 } 
				 }
			}
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		 return null;
	 }
	 
	 private <T> List<T> retrieveListParam(HttpServletRequest request, Class<T> klass, String parentFieldName){
		 List<T> paramList = new ArrayList<T>();
		 
		 try{
			 Constructor<T> constructor = klass.getConstructor();
			 T t = constructor.newInstance();
			 List<Field> fields = (t instanceof BaseEntity) ? ((BaseEntity)t).getAllField() : Arrays.asList(klass.getDeclaredFields());
			 int paramLength = 0;
			 for(Field field : fields){
				 if(field.getClass().getModifiers() == Modifier.FINAL || field.getClass().getModifiers() == Modifier.STATIC)
						continue;
				 
				 String listFieldName = parentFieldName + "."+ field.getName();
				 if(request.getParameterValues(listFieldName)!=null){
					 int requestLength = request.getParameterValues(listFieldName).length;
					 if(requestLength > paramLength)
						 paramLength = requestLength;
				 }
				 
			 }
			 
			 
			 for(int i = 0; i <paramLength; i++){
				 t = constructor.newInstance();
				 for(Field field : fields){
					 if(field.getClass().getModifiers() == Modifier.FINAL || field.getClass().getModifiers() == Modifier.STATIC)
							continue;
					 
					 String listFieldName = parentFieldName + "."+ field.getName();
					 String fieldTypeName = field.getType().getName();
					 
					 if (fieldTypeName.startsWith("sg.edu.nus.iss.t4p.phoenix.entity")){
						 // process for inner field
						 Class<?> fieldClass = Class.forName(fieldTypeName);
						 Object fieldObject = fieldClass.getConstructor().newInstance();
						
						 List<Field> _innerFields = (fieldObject instanceof BaseEntity) ? ((BaseEntity)fieldObject).getAllField() : Arrays.asList(fieldObject.getClass().getDeclaredFields());
						 for(Field _innerField : _innerFields){
							 if(_innerField.getClass().getModifiers() == Modifier.FINAL || _innerField.getClass().getModifiers() == Modifier.STATIC)
									continue;
							 
							 String innerFieldParamName = listFieldName+"."+_innerField.getName();
							 
							 if(request.getParameterValues(innerFieldParamName)==null || i > request.getParameterValues(innerFieldParamName).length - 1)
								 continue;
							 String value = request.getParameterValues(innerFieldParamName)[i];
							 if(!T4StringUtil.isEmpty(value)){
								 _innerField.setAccessible(true);
								 _innerField.set(fieldObject, this.valueConvert(fieldObject, _innerField, value));
							 } 
						 }
						 field.setAccessible(true);
						 field.set(t, fieldObject);
					 }else{
						 
						 if(request.getParameterValues(listFieldName)==null || i > request.getParameterValues(listFieldName).length - 1)
							 continue;
						 
						 String value = request.getParameterValues(listFieldName)[i];
						 if(!T4StringUtil.isEmpty(value)){
							 field.setAccessible(true);
							 field.set(t, this.valueConvert(t, field, value));
						 } 
					 }
				 }
				 paramList.add(t);
			 }
			 
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 
		 return paramList;
	 }
	 
	 private Object valueConvert(Object obj, Field field,String value) throws ParseException{
		 Class<?> dataType = field.getType();
		 
		 if(field.getType().isPrimitive()){
			 if(dataType.equals(Integer.TYPE))
				 return Integer.parseInt(value);
			 else if (dataType.equals(Long.TYPE))
				 return Long.parseLong(value);
			 else if (dataType.equals(Double.TYPE))
				 return Double.parseDouble(value);
			 else if (dataType.equals(Float.TYPE))
				 return Float.parseFloat(value);
		 }else{
			 if(dataType.equals(Integer.class))
				 return Integer.parseInt(value);
			 else if (dataType.equals(Long.class))
				 return Long.parseLong(value);
			 else if (dataType.equals(Double.class))
				 return Double.parseDouble(value);
			 else if (dataType.equals(Float.class))
				 return Float.parseFloat(value);
			 else if (dataType.equals(BigDecimal.class))
				 return new BigDecimal(value);
			 else if (dataType.equals(Date.class))
				 return T4DateUtil.saveParseDate(value);
			 else
				 return value;
		 }
		 return null;
	 }


}
