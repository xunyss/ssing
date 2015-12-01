package com.xunyss.ssing.test;

import com.xunyss.ssing.xa.dataset.IXAQuery;
import com.xunyss.ssing.xa.dataset.events._IXAQueryEvents;
import com.xunyss.ssing.xa.session.IXASession;
import com.xunyss.ssing.xa.session.events._IXASessionEvents;
import com4j.EventCookie;

public class SimpleTest {
	
	public static void main(String[] args) throws Exception {
		new SimpleTest().run();
	}
	
	IXASession iXASession;
	IXAQuery iXAQuery1, iXAQuery2;
	
	public void run() throws Exception {
		System.out.println("program start..");
		
		iXASession = com.xunyss.ssing.xa.session.ClassFactory.createXASession();
		EventCookie cookie1 = iXASession.advise(_IXASessionEvents.class, new _IXASessionEvents() {
			@Override
			public void login(String szCode, String szMsg) {
				System.out.println(szCode + " : " + szMsg);
				
				int c = iXASession.getAccountListCount();
				System.out.println(c);
				
				String a = iXASession.getAccountList(0);
				System.out.println(a);
				
				String n = iXASession.getAccountName(a);
				System.out.println(n);
			}
		});
		System.out.println(cookie1);
		
		iXASession.disconnectServer();
		
	//	boolean r = iXASession.connectServer("hts.etrade.co.kr", 20001);
		boolean r = iXASession.connectServer("demo.etrade.co.kr", 20001);
		System.out.println("connectServer : " + r);
		
		iXASession.login("xuny", "password", "certpass", 0, true);
		
		Thread.sleep(2500);
		
		
		iXAQuery1 = com.xunyss.ssing.xa.dataset.ClassFactory.createXAQuery();
		iXAQuery2 = com.xunyss.ssing.xa.dataset.ClassFactory.createXAQuery();
		
		_IXAQueryEvents myeventhandler = new _IXAQueryEvents() {
			@Override
			public void receiveData(String szTrCode) {
				System.out.println("receiveData : " + szTrCode);
				
				if ("t1101".equals(szTrCode)) {
					String b = "t1101OutBlock";
					String r1 = iXAQuery1.getFieldData(b, "hname", 0);
					String r2 = iXAQuery1.getFieldData(b, "price", 0);
					
					System.out.println("--------------------------------------");
					System.out.println(r1 + ";" + r2);
				}
				else if ("t8430".equals(szTrCode)) {
					int c = iXAQuery2.getBlockCount("t8430OutBlock");
					System.out.println("--------------------------------------");
					for (int i = 0; i < c; i++) {
						String r1 = iXAQuery2.getFieldData("t8430OutBlock", "hname", i);
						String r2 = iXAQuery2.getFieldData("t8430OutBlock", "shcode", i);
						System.out.println(r1 + " " + r2);
					}
				}
			}
		};
		
		iXAQuery1.advise(_IXAQueryEvents.class, myeventhandler);
		iXAQuery2.advise(_IXAQueryEvents.class, myeventhandler);
		
		System.out.println("request TR(t1101)");
		iXAQuery1.resFileName("\\res\\t1101.res");
		iXAQuery1.setFieldData("t1101InBlock", "shcode", 0, "000660");
		iXAQuery1.request(false);
		
		Thread.sleep(1000);
		
		System.out.println("request TR(t8430)");
		iXAQuery2.resFileName("\\res\\t8430.res");
		iXAQuery2.setFieldData("t8430InBlock", "gubun", 0, "1");
		iXAQuery2.request(false);
		
		Thread.sleep(5000);
	}
}
