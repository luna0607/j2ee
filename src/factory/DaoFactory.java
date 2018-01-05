package factory;

import dao.OrderlistDao;
import dao.UserDao;
import dao.impl.OrderlistDaoImpl;
import dao.impl.UserDaoImpl;


public class DaoFactory {

	public static OrderlistDao getOrderlistDao()
	{
		return OrderlistDaoImpl.getInstance();
	}
	
	public static UserDao getUserDao()
	{
		return UserDaoImpl.getInstance();
	}

}
