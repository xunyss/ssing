package io.xunyss.ssing.web.trade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.xunyss.ssing.api.XtsConnection;

/**
 * 
 * @author XUNYSS
 */
@Controller
@RequestMapping("/trade")
public class TradeController {
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	XtsConnection xconn = new XtsConnection();
	
	@RequestMapping("/query")
	public String query() {
		
		return "query";
	}
	
	@RequestMapping("/t1")
	public String t1() {
		xconn.connect();
		return "query";
	}
	
	@RequestMapping("/t2")
	public String t2() {
		xconn.login();
		return "query";
	}
	
	@RequestMapping("/debug")
	public String debug() {
		xconn.debug();
		return "query";
	}
}
