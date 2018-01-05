package factory;

import dao.OrderDao;
import dao.CustomerDao;
import dao.impl.OrderDaoImpl;
import dao.impl.CustomerDaoImpl;


public class DaoFactory {

	public static OrderDao getOrderlistDao()
	{
		return OrderDaoImpl.getInstance();
	}
	
	public static CustomerDao getUserDao()
	{
		return CustomerDaoImpl.getInstance();
	}

}
