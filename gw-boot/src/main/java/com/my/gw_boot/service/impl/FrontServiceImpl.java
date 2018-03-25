package com.my.gw_boot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.my.gw_boot.service.FrontService;
import com.my.gw_boot.config.SpringConfig;
import com.my.gw_boot.mapper.FrontMapper;
import com.my.gw_boot.netty.NettyClient;


@Service("frontServiceImpl")
public class FrontServiceImpl implements FrontService {

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
			
			responseMsg = client.send(	springConfig.getBackServerIp(), 
										springConfig.getBackServerPort(), 
										requestMsg, 
										springConfig.getBackServerTimeout());
			
			if("testMsg[6".equals(requestMsg))
				log.info("frontMapper.selectDBConnectTest[" + frontMapper.selectDBConnectTest());
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return responseMsg;
	}

	
}
