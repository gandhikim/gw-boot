package com.my.gw_boot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.my.gw_boot.config.SpringConfig;
import com.my.gw_boot.mapper.FrontMapper;
import com.my.gw_boot.netty.NettyClient;
import com.my.gw_boot.service.MyService;


@Service("frontServiceImpl")
public class FrontServiceImpl implements MyService {

	private final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(FrontServiceImpl.class);
	
	@Autowired
	private FrontMapper frontMapper;
	
	@Autowired
	@Qualifier("springConfig")
	private SpringConfig springConfig;
	
	NettyClient client = new NettyClient();
	
	public String service(String requestMsg) throws Exception {
		
		log.info( "FrontServiceImpl - Service - Start." );
		
		String responseMsg = null;
		
		try {
			
			// gw : send msg
			responseMsg = client.send(	springConfig.getBackServerIp(), 
										springConfig.getBackServerPort(), 
										requestMsg, 
										springConfig.getBackServerTimeout());

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return responseMsg;
	}

	
}
