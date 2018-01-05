package factory;

import service.OnlineUserService;
import service.impl.OnlineUserServiceImpl;

public class ServiceFactory {

	public static OnlineUserService getVisitorCounterService()
	{
		return OnlineUserServiceImpl.getInstance();
	}
	
}
