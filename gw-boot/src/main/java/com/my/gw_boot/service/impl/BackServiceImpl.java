package com.my.gw_boot.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.gw_boot.mapper.FrontMapper;
import com.my.gw_boot.service.MyService;


@Service("backServiceImpl")
public class BackServiceImpl implements MyService {

	private final Logger log = LoggerFactory.getLogger(BackServiceImpl.class);
	
	@Autowired
	private FrontMapper frontMapper;

	public String service(String requestMsg) throws Exception {
		
		log.info( "BackServiceImpl - Service - Start." );
		
		String responseMsg = null;
		
		try {
			
			responseMsg = requestMsg;
			
			if("testMsg[6".equals(requestMsg))
				log.info("frontMapper.selectDBConnectTest[" + frontMapper.selectDBConnectTest());
			
					
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return responseMsg;
	}
	
}
