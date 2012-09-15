package sg.edu.nus.iss.t4p.phoenix.core.dao;

import sg.edu.nus.iss.t4p.phoenix.dao.programslot.ProgramSlotDao;
import sg.edu.nus.iss.t4p.phoenix.dao.programslot.impl.ProgramSlotDaoImpl;
import sg.edu.nus.iss.t4p.phoenix.dao.role.RoleDao;
import sg.edu.nus.iss.t4p.phoenix.dao.role.impl.RoleDaoImpl;
import sg.edu.nus.iss.t4p.phoenix.dao.user.UserDao;
import sg.edu.nus.iss.t4p.phoenix.dao.user.impl.UserDaoImpl;

public class DaoFactory {
	
	/** SINGLETON ******************************/
	private static DaoFactory instance;
	private DaoFactory(){/*ENSURE SINGLETON*/
		this.userDao = UserDaoImpl.getInstance();
		this.programSlotDao = ProgramSlotDaoImpl.getInstance();
		this.roleDao = RoleDaoImpl.getInstance();
	}
	public static DaoFactory getInstance(){
		if(instance == null)
			instance = new DaoFactory();
		
		return instance;
	}
	
	/** VARIABLES ******************************/
	private UserDao userDao;
	public UserDao getUserDao(){return this.userDao;}
	
	private ProgramSlotDao programSlotDao;
	public ProgramSlotDao getProgramSlotDao(){return this.programSlotDao;}
	
	private RoleDao roleDao;
	public RoleDao getRoleDao(){ return this.roleDao;}
	
}
