package factory;

import service.VisitorCounterService;
import service.VisitorCounterServiceBean;

public class ServiceFactory {

	public static VisitorCounterService getVisitorCounterService()
	{
		return VisitorCounterServiceBean.getInstance();
	}
	
}
