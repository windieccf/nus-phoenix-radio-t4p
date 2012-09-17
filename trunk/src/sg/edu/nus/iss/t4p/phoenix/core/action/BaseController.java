package sg.edu.nus.iss.t4p.phoenix.core.action;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.edu.nus.iss.t4p.phoenix.utility.T4StringUtil;


@SuppressWarnings("serial")
public abstract class BaseController extends HttpServlet {
	
	public BaseController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Process requests from clients.
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
	 
	 protected void doRedirect(HttpServletRequest request,	HttpServletResponse response, String path) throws IOException{
		 response.sendRedirect(request.getContextPath()+"/"+path);
	 }
	 
	 /** Shorthand method ********************************************************/
	 protected <T> T retrieveParameter(HttpServletRequest request , Class<T> klass){
		 return this.retrieveParameter(request, klass, null);
	 }
	 
	 protected <T> T retrieveParameter(HttpServletRequest request , Class<T> klass, String paramName){
		 
		 try {
			 String prefix = T4StringUtil.decapitalize( (T4StringUtil.isEmpty(paramName) ) ? klass.getSimpleName(): paramName);
			 
			 Constructor<T> constructor = klass.getConstructor();
			 T t = constructor.newInstance();
			 Field[] fields = klass.getDeclaredFields();
			 for(Field field : fields){
				 if(field.getClass().getModifiers() == Modifier.FINAL || field.getClass().getModifiers() == Modifier.STATIC)
					continue;
				
				String fieldName = prefix + "." + field.getName();
				String value = request.getParameter(fieldName);
				if(!T4StringUtil.isEmpty(value)){
					field.setAccessible(true);
					field.set(t, this.valueConvert(t, field, value));
				}
			}
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		 return null;
	 }
	 
	 private Object valueConvert(Object obj, Field field,String value){
		 Class<?> dataType = field.getType();
		 
		 if(field.getType().isPrimitive()){
			 if(dataType.equals(Integer.TYPE))
				 return Integer.parseInt(value);
			 else if (dataType.equals(Double.TYPE))
				 return Double.parseDouble(value);
			 else if (dataType.equals(Float.TYPE))
				 return Float.parseFloat(value);
		 }else{
			 if(dataType.equals(Integer.class))
				 return Integer.parseInt(value);
			 else if (dataType.equals(Double.class))
				 return Double.parseDouble(value);
			 else if (dataType.equals(Float.class))
				 return Float.parseFloat(value);
			 else if (dataType.equals(BigDecimal.class))
				 return new BigDecimal(value);
			 else
				 return value;
		 }
		 return null;
	 }


}
