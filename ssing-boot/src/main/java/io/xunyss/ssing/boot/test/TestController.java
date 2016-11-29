package io.xunyss.ssing.boot.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("/test/connect")
	public ModelMap connect() {
		
		log.debug("TEST: {}", "connect");
		
		ModelMap model = new ModelMap();
		model.addAttribute("result", true);
		
		return model;
	}
}
