package sg.edu.nus.iss.t4p.phoenix.core.dao;

import sg.edu.nus.iss.t4p.phoenix.dao.user.UserDao;
import sg.edu.nus.iss.t4p.phoenix.dao.user.impl.UserDaoImpl;

public class DaoFactory {
	
	/** SINGLETON ******************************/
	private static DaoFactory instance;
	private DaoFactory(){/*ENSURE SINGLETON*/
		this.userDao = UserDaoImpl.getInstance();
	}
	public static DaoFactory getInstance(){
		if(instance == null)
			instance = new DaoFactory();
		
		return instance;
	}
	
	/** VARIABLES ******************************/
	private UserDao userDao;
	public UserDao getUserDao(){return this.userDao;}
	
	
}
