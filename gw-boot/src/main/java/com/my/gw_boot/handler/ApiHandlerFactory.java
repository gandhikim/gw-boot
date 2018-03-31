package com.my.gw_boot.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.my.gw_boot.config.SpringConfig;
import com.my.gw_boot.service.BackService;
import com.my.gw_boot.service.FrontService;
import com.my.gw_boot.service.MyService;

@Component("apiHandlerFactory")
public class ApiHandlerFactory {
	
private final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger( ApiHandlerFactory.class );
	
	@Autowired
	@Qualifier("springConfig")
	private SpringConfig springConfig;
	
	@Autowired
	@Qualifier("frontServiceImpl")
	private MyService frontService;
	
	@Autowired
	@Qualifier("backServiceImpl")
	private MyService backService;

	public MyService getMyService(String service) {
        service = service.toLowerCase();

        if (service.equals("front")) {
            return (MyService) frontService;
        } else if (service.equals("back")) {
            return (MyService) backService;
        } else {
            return null;
        }
    }
	
	/*public static ApiHandler create(String demonType) throws Exception {

		if (demonType.equals("front")) {
			return new FrontApiHandler();
		} else if (demonType.equals("card")) {
			return new CardApiHandler();
		} else if (demonType.equals("vacct")) {
			return new VacctApiHandler();
		} else {
			return null;
		}
	}*/
}
