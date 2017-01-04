package io.xunyss.ssing.boot.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.xunyss.ssing.api.Block;
import io.xunyss.ssing.api.Query;
import io.xunyss.ssing.api.Session;
import io.xunyss.ssing.boot.service.TradeService;
import io.xunyss.ssing.xa.dataset.ClassFactory;
import io.xunyss.ssing.xa.dataset.IXAQuery;
import io.xunyss.ssing.xa.dataset.IXAReal;
import io.xunyss.ssing.xa.dataset.events._IXAQueryEvents;
import io.xunyss.ssing.xa.dataset.events._IXARealEvents;

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
	
	@RequestMapping("/now")
	public String now() {
		
//		final IXAQuery iq = ClassFactory.createXAQuery();
//		iq.advise(_IXAQueryEvents.class, new _IXAQueryEvents() {
//			@Override
//			public void receiveData(String szTrCode) {
//				log.debug("receiveData : " + szTrCode);
//
//				String block = "t1101OutBlock";
//				
//				String of1 = iq.getBlockData(block);	// row전체데이터
//				log.debug("block data: {}", of1);
//				
//			//	iq.clearBlockdata(block);	// block 데이터 지우기 ㅋㅋㅋ
//				
//				String r1 = iq.getFieldData(block, "hname", 0);
//				String r2 = iq.getFieldData(block, "price", 0);
//				log.debug("--------------------------------------");
//				log.debug(r1 + ": " + r2);
//				
//				int errcode = iq.getLastError();
//				String errmsg = iq.getErrorMessage(errcode);
//				log.debug("{}, {}", errcode, errmsg);
//				
//				int ac = iq.getAccountListCount();
//				String an = iq.getAccountList(0);
//				log.debug("{}, {}", ac, an);
//				
//				String attr = iq.getAttribute("t1101OutBlock", "hname", "Use", 0);
//				log.debug("attr: {}", attr);
//				
//				////////////////////////////////////
//			//	int type = iq.getBlockSize("t1101OutBlock");	// 더이상지원하지않음
//			//	System.err.println(type + 1);
//			//	String ss = iq.getFieldDescList("t1101OutBlock");	// 더이상지원하지않음
//			//	System.err.println(ss);
//				
//				iq.dispose();
//			}
//		});
//		iq.resFileName("\\res\\t1101.res");
//		iq.setFieldData("t1101InBlock", "shcode", 0, "000660");
//		
//		int res = iq.request(false);
//		log.debug("request result: {}", res);
		
		///////////////////////////////////////////////////////////
		
		Block block = new Block("t1101InBlock");
		block.set(0, "shcode", "000660");
		
		Query query = Query.create("t1101");
		query.setBlock(block);
		query.request();
		
		return "OK";
	}
	
	
	@RequestMapping("/juga")
	public String juga() {
		
		final IXAQuery iq = ClassFactory.createXAQuery();
		iq.advise(_IXAQueryEvents.class, new _IXAQueryEvents() {
			@Override
			public void receiveData(String szTrCode) {
				log.debug("receiveData : " + szTrCode);
				
				boolean isnext = iq.isNext();		// 다음에 조회할게 있으면..........
				log.debug("idNext: {}", isnext);	// true
				
				String ob = "t1305OutBlock";
			//	int size = iq.getBlockSize(ob);		// 더이상 지원하지 않음
				int size = iq.getBlockCount(ob);	// setBlockCount 는 input 에만 사용 - 그런게 있나...
				log.debug("block size: {}", size);
				
				String of1 = iq.getBlockData(ob);	// row전체데이터 -> 제대로 안나오는 것들이 있음
				log.debug("outBlock data: {}", of1);
				
				String of2 = iq.getBlockData(ob + "1");
				log.debug("outBlock1 data: {}", of2);
				
				String v1 = iq.getFieldData(ob, "cnt", 0);
				String v2 = iq.getFieldData(ob, "date", 0);
				String v3 = iq.getFieldData(ob, "idx", 0);
				log.debug("fields: {}, {}, {}", v1, v2, v3);
				
			//	String fdesc = iq.getFieldDescList(ob);	// 더이상 지원하지 않음
			//	log.debug("fields desc: {}", fdesc);
				
				String ss = iq.continueKey();		// 이게 뭘까..............
				log.debug("continue key: {}", ss);	// 이게 뭘까..............
				
				iq.dispose();
			}
		});
		iq.resFileName("\\res\\t1305.res");
		iq.setFieldData("t1305InBlock", "shcode", 0, "000030");
		iq.setFieldData("t1305InBlock", "dwmcode", 0, "1");
		iq.setFieldData("t1305InBlock", "date", 0, "");
		iq.setFieldData("t1305InBlock", "idx", 0, "0");
		iq.setFieldData("t1305InBlock", "cnt", 0, "10");
		
		int res = iq.request(false);
		log.debug("juga request result: {}", res);
		
		return "juga";
	}
	
	@RequestMapping("/conclude")
	public String r() {
		
		final IXAReal ir = ClassFactory.createXAReal();
		ir.resFileName("\\res\\S3_.res");
		ir.setFieldData("InBlock", "shcode", "000030");
		
		ir.adviseRealData();
		ir.advise(_IXARealEvents.class, new _IXARealEvents() {
			@Override
			public void receiveRealData(String szTrCode) {
				String t = "";
				try {
					t = ir.getFieldData("OutBlock", "cvolume");
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(">>> " + szTrCode + ": " + t);
			}

			@Override
			public void recieveLinkData(String szLinkName, String szData, String szFiller) {
				System.out.println(">>>>>>>>>>>>> recieveLinkData......");
			}
		});
		
		return "real finished";
	}
	
	@RequestMapping("/tr")
	public String tr() {
		IXAQuery iq = ClassFactory.createXAQuery();
		
//		iq.loadFromResFile("\\res\\t1101.res");
	//	iq.resFileName("\\res\\t1101.res");
		iq.resFileName("\\res\\t1305.res");//hasNextTR
		
		String trCode = iq.getTrCode();
		String trDesc = iq.getTrDesc();
		String res = iq.resFileName();
		String resData = iq.getResData();
	//	String field = iq.getFieldData("t1101InBlock", "shcode", 0);	// response 이후..
		boolean isnext = iq.isNext();				// response 이후
	//	int type = iq.getBlockType("t1101InBlock");		// ** 사용금지: 더이상 지원하지 않음.!
	//	int cnt = iq.getTRCountPerSec("t1101");		// static 함수로...
		
		log.debug("trCode: {}", trCode);
		log.debug("trDesc: {}", trDesc);
		log.debug("resFile: {}", res);
		log.debug("resData: {}", resData);
	//	log.debug("field: {}", field);
		log.debug("isNext: {}", isnext);
	//	log.debug("type: {}", type);
	//	log.debug("TR cnt: {}", cnt);
		
		log.debug("TR per sec: {}", iq.getTRCountPerSec("t1305"));	// tr의 초당 전송가능 횟수
		log.debug("TR request: {}", iq.getTRCountRequest("t1305"));	// 10분내 요청한 횟수 (근데 계속 1로 나옴)
		log.debug("TR base sec: {}", iq.getTRCountBaseSec("t1305"));//TR의Base시간(초단위)를취득합니다.
		log.debug("TR limit: {}", iq.getTRCountLimit("t8430"));//???????
		
		log.debug("---------------------------------------");
		////////////////
//		String s1=null, s2=null;
//		int s3=0;
//		Holder<String> in1 = new Holder<>(s1);
//		Holder<String> in2 = new Holder<>(s2);
//		Holder<Integer> in3 = new Holder<>(s3);
//		iq.getBlockInfo("shcode", in1, in2, in3);	// ** 사용금지: 더이상 지원하지 않음.!
//		log.debug("out: {}, {}, {}", s1, s2, s3);
//		iq.getFieldInfo("shcode", null, null, null, null, null);	// ** 사용금지: 더이상 지원하지 않음.!
		
		//////////
		String ss = iq.continueKey();
		log.debug("continue key: {}", ss);
		
		
		return "tr";
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
