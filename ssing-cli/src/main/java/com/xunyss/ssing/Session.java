package com.xunyss.ssing;

import com.xunyss.ssing.xa.session.ClassFactory;
import com.xunyss.ssing.xa.session.IXASession;
import com.xunyss.ssing.xa.session.events._IXASessionEvents;
import com4j.EventCookie;

/**
 * 
 * @author XUNYSS
 */
public class Session {
	
	/**
	 * 
	 */
	private static class Holder {
		private static final Session instance = new Session();
	}
	
	/**
	 * 
	 * @return
	 */
	public static Session getInstance() {
		return Holder.instance;
	}
	
	/**
	 * 
	 */
	private Session() {
		init();
	}
	
	
	private IXASession iXASession = null;
	
	private boolean isConnect = false;
	private boolean isLogin = false;
	
	private void init() {
		iXASession = ClassFactory.createXASession();
	}
	
	public boolean isConnect() {
		return isConnect;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public boolean login(String username, String password, String certpass) {
		EventCookie sessionCallback = iXASession.advise(_IXASessionEvents.class, new _IXASessionEvents() {
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
				System.out.println("###################");
				System.out.println("Session callback - disconnect() 수행");
			}
		});
		
		// 이미 접속되어 있으면 접속 해제
		iXASession.disconnectServer();
		
		// 실서버 : hts.ebestsec.co.kr:20001
		// 모의투자 : demo.ebestsec.co.kr:20001
		if (iXASession.connectServer("hts.ebestsec.co.kr", 20001)) {
			isConnect = true;
			
			// arguments
			// ID, 비밀번호, 공인인증비밀번호, 실서버:0|모의투자서버:1, 공인인증에러시 메시지창 표시여부
			if (iXASession.login(username, password, certpass, 0, false)) {
				isLogin = true;
			}
			else {
				iXASession.disconnectServer();
				isConnect = false;
			}
		}
		else {
			isConnect = false;
			isLogin = false;
		}
		
		return isLogin;
	}
	
	public void logout() {
		if (isLogin) {
			iXASession.disconnectServer();
			iXASession.dispose();
			
			isConnect = false;
			isLogin = false;
		}
	}
}
