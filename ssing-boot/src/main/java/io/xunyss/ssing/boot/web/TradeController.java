package io.xunyss.ssing.boot.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.xunyss.ssing.api.Session;
import io.xunyss.ssing.boot.service.TradeService;
import io.xunyss.ssing.xa.dataset.ClassFactory;
import io.xunyss.ssing.xa.dataset.IXAQuery;
import io.xunyss.ssing.xa.dataset.events._IXAQueryEvents;

@RestController
@RequestMapping("/trade")
public class TradeController {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("/connect")
	public String connect() {
		Session xsConn = Session.getInstance();
		boolean ret = xsConn.connect();
		
		log.debug("connect result: {}", ret);
		log.debug("connected: {}", xsConn.isConnected());
		
		return "OK";
	}
	
	@RequestMapping("/login")
	public String login() {
		Session xsConn = Session.getInstance();
		boolean ret = xsConn.login();
		
		log.debug("login result: {}", ret);
		log.debug("logined: {}", xsConn.isLogined());
		
		return "OK";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		Session xsConn = Session.getInstance();
		boolean ret = xsConn.logout();
		
		log.debug("logout result: {}", ret);
		log.debug("logined: {}", xsConn.isLogined());
		
		return "OK";
	}
	
	@RequestMapping("/disconnect")
	public String disconnect() {
		Session xsConn = Session.getInstance();
		xsConn.disconnect();
		
		log.debug("connected: {}", xsConn.isConnected());
		
		return "OK";
	}
	
	@RequestMapping("/status")
	public String status() {
		
		return "OK";
	}
	
	@RequestMapping("/account")
	public List<?> account() {
		Session xsConn = Session.getInstance();
		return xsConn.getAccountList();
	}
	
	@Autowired
	TradeService service;
	
	@RequestMapping("/jongmok")
	public String jongmok() {
		
		final IXAQuery iq = ClassFactory.createXAQuery();
		iq.advise(_IXAQueryEvents.class, new _IXAQueryEvents() {
			@Override
			public void receiveData(String szTrCode) {
				System.out.println("receiveData : " + szTrCode);
				
				service.save(iq);
				
				iq.dispose();	// 없으면 프로그램 끝날때 에러 나던데...
			}
		});
		iq.resFileName("\\res\\t8430.res");
		iq.setFieldData("t8430InBlock", "gubun", 0, "0");
		
		int res = iq.request(false);
		
		
		
		return "OK";
	}
	
	
	
	
	
	
	
	
	
	@Autowired
	ApplicationContext context;
	
	@RequestMapping("/ctx")
	public String ctx() {
		log.debug(context.toString());
		Object bean = context.getBean(TradeService.class);
		log.debug(bean.getClass().getName());
		log.debug(service.getClass().getName());
		
		boolean proxy = AopUtils.isAopProxy(bean);
		log.debug("proxy: {}", proxy);
		log.debug(AopUtils.getTargetClass(bean).getName());
		
		return "OK";
	}
}
