package factory;

import service.VisitorCounterService;
import service.impl.VisitorCounterServiceImpl;

public class ServiceFactory {

	public static VisitorCounterService getVisitorCounterService()
	{
		return VisitorCounterServiceImpl.getInstance();
	}
	
}
