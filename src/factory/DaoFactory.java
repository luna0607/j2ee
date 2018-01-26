package factory;

import dao.OrderlistDao;
import dao.OrderlistDaoBean;
import dao.UserDao;
import dao.UserDaoBean;


public class DaoFactory {

	public static OrderlistDao getOrderlistDao()
	{
		return OrderlistDaoBean.getInstance();
	}
	
	public static UserDao getUserDao()
	{
		return UserDaoBean.getInstance();
	}

}
