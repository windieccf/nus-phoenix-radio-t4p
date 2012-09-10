package sg.edu.nus.iss.t4p.phoenix.entity.scalar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MessageEntity implements Serializable{
	
	private List<String> errMessage = new ArrayList<String>();
	private List<String> infoMessage = new ArrayList<String>() ;

}
