package sg.edu.nus.iss.t4p.phoenix.utility;

import java.text.DecimalFormat;
import java.util.List;

public class T4StringUtil {
	
	private T4StringUtil(){/*ENSURE NO CREATIONAL OF THE CLASS*/}
	public static final DecimalFormat FORMAT_01 = new DecimalFormat("00");
	
	
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
	
	
	public static String join(List<String> texts, String regex){
    	StringBuffer sb = new StringBuffer();
    	if(texts == null || texts.isEmpty()) 
			return "";
    	
    	for(int i = 0 ; i < texts.size(); i++){
    		sb.append(texts.get(i));
    		if(!T4StringUtil.isEmpty(regex)  && i != texts.size() -1 )
    			sb.append(regex);
    	}
    	return sb.toString();
    }
	

}
