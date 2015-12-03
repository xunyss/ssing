package com.xunyss.ssing.web.trade;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author xuny
 */
@Controller
@RequestMapping("/trade")
public class TradeController {
	
	@RequestMapping("/query")
	public String query() {
		
		return "query";
	}
}
