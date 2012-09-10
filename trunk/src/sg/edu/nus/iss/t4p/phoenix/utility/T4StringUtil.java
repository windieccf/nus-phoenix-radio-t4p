package sg.edu.nus.iss.t4p.phoenix.utility;

public class T4StringUtil {
	
	private T4StringUtil(){/*ENSURE NO CREATIONAL OF THE CLASS*/}
	
	public static String translateUrlToMethod(String pathInfo){
		int pos = pathInfo.indexOf(".");
        int len = pathInfo.length();
        
        String prefix = pathInfo.substring(pos+1,len);
        String suffix = capitalize(pathInfo.substring(1,pos));
        
		return prefix+suffix;
	}
	
	public static String capitalize(String text){
		if(isEmpty(text)) 
			return text;
		
		return Character.toUpperCase(text.charAt(0)) + text.substring(1);
	}
	
	public static String decapitalize(String text){
		if(isEmpty(text)) 
			return text;
		
		return Character.toLowerCase(text.charAt(0)) + text.substring(1);
	}
	
	
	public static boolean isEmpty(String text){
		return (text == null || "".equals(text.trim())) ;
	}

}
