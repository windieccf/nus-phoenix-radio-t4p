package sg.edu.nus.iss.t4p.phoenix.core.enumeration;

public enum UrlPathEnum {
	ACTION_LOGIN("/authenticate.do", "/controller/authenticate.do" ,"/authenticateController/login.do"),
	ACTION_LOGOUT("/logout.do", "/controller/logout.do" ,"/authenticateController/logout.do"),
	ACTION_LIST_SCHEDULE("/listSchedule.do", "/controller/listSchedule.do" ,"/scheduleController/list.do"),
	
	
	PAGE_HOME("/", "/" ,"/");
	
	private String controlPath;
	public String getControlPath() {return controlPath;}
	
	private String frontControlPath;
	public String getFrontControlPath() {return frontControlPath;}
	
	private String destinationPath;
	public String getDestinationPath() {return destinationPath;}

	UrlPathEnum(String controlPath , String frontControlPath, String destinationPath){
		this.controlPath = controlPath;
		this.frontControlPath = frontControlPath;
		this.destinationPath = destinationPath;
	}
	
	public static UrlPathEnum getByControlPath(String path){
		for(UrlPathEnum urlPath : UrlPathEnum.values()){
			if(urlPath.getControlPath().equals(path))
				return urlPath;
		}
		
		return PAGE_HOME;
	}
	
}
