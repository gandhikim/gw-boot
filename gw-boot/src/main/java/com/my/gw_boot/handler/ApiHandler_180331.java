package com.my.gw_boot.handler;

/*
 * replace to ApiHandlerFactory
 * Not use
 * 
 * */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.my.gw_boot.config.SpringConfig;
import com.my.gw_boot.service.MyService;


@Component("apiHandler_180331")
public class ApiHandler_180331 {
	
	private final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger( ApiHandler_180331.class );
	
	@Autowired
	@Qualifier("springConfig")
	private SpringConfig springConfig;
	
	@Autowired
	@Qualifier("frontServiceImpl")
	private MyService frontService;
	//private FrontService frontService;
	
	@Autowired
	@Qualifier("backServiceImpl")
	private MyService backService;
	//private BackService backService;
	
	public String doProcess( String requestMsg ) throws Exception {
		
		log.info( "ApiHandler - doProcess - Start." );
		
		String responseMsg = null;
		
		if ("front".equals(springConfig.getDemonType())) {
			responseMsg = frontService.service(requestMsg);
		} else if ("back".equals(springConfig.getDemonType())) {
			responseMsg = backService.service(requestMsg);
		} else {
			// echo
			responseMsg = requestMsg;
		}
		
		return responseMsg;
		
	}

}
