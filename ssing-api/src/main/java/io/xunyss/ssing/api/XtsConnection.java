package io.xunyss.ssing.api;

import io.xunyss.ssing.xa.session.ClassFactory;
import io.xunyss.ssing.xa.session.IXASession;
import io.xunyss.ssing.xa.session.events._IXASessionEvents;

/**
 * 
 * @author XUNYSS
 */
public class XtsConnection {

	private boolean connected = false;
	private boolean logined = false;
	
	public boolean isConnected() {
		return connected;
	}
	public boolean isLogined() {
		return logined;
	}
	
	private IXASession iXASession = null;
	
	public XtsConnection() {
		iXASession = ClassFactory.createXASession();
		
		iXASession.advise(_IXASessionEvents.class, new _IXASessionEvents() {
			
			@Override
			public void login(String szCode, String szMsg) {
				// szCode 가 "0000" 일 경우 성공 아니면 실패
				System.out.println(szCode);
				System.out.println(szMsg);
				System.out.println(iXASession.getServerName());
			}

			@Override
			public void logout() {
				// callback 안됨
			}

			@Override
			public void disconnect() {
				System.out.println("##################");
				System.out.println("Session callback - disconnect() 수행");
			}
		});
	}
	
	public void connect() {
		iXASession.connectServer("hts.ebestsec.co.kr", 30001);
	}
	
	public void login() {
		boolean ret = iXASession.login("xuny", "password", "certpass", 0, true);
		System.err.println("login: " + ret);
	}
	
	public void debug() {
		System.err.println(iXASession.isConnected());
		System.err.println(iXASession.getAccountListCount());
		System.err.println(iXASession.getAccountList(0));
	}
}
