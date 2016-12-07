package io.xunyss.ssing.api;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.xunyss.ssing.xa.session.ClassFactory;
import io.xunyss.ssing.xa.session.IXASession;
import io.xunyss.ssing.xa.session.events._IXASessionEvents;

/**
 * 
 * @author XUNYSS
 */
public class Session {
	
	//--------------------------------------------------------------------------
	// singleton
	//--------------------------------------------------------------------------
	
	/**
	 * 
	 */
	private static class InstanceHolder {
		private static final Session instance = new Session();
	}
	
	/**
	 * 
	 * @return
	 */
	public static Session getInstance() {
		return InstanceHolder.instance;
	}
	
	/**
	 * 
	 */
	private Session() {
		initialize();
	}
	
	
	//--------------------------------------------------------------------------
	// session
	//--------------------------------------------------------------------------
	
	/**
	 * logger
	 */
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	private IXASession xaSession = null;
	
	private boolean logined = false;
	private boolean waiting = false;
	
	
	/**
	 * 
	 */
	private void initialize() {
		// create COM instance
		xaSession = ClassFactory.createXASession();
		xaSession.setName(getClass().getName() + ".xaSession");
		
		// bind event handler
		xaSession.advise(_IXASessionEvents.class, new _IXASessionEvents() {
			
			/**
			 * @param szCode	"0000" �� ��� ���� �ƴϸ� ����
			 * @param szMsg		��� �޽���
			 */
			@Override
			public void login(String szCode, String szMsg) {
				szCode = szCode.trim();
				szMsg = szMsg.trim();
				log.debug("login callback: [{}] {}", szCode, szMsg);
				
				// set result
				logined = "0000".equals(szCode);
				
				// resume method: login()
				getInstance().wakeup();
			}
			
			@Override
			public void logout() {		// callback �ȵ�
				log.info("logout(callback)");
			}
			
			@Override
			public void disconnect() {
				log.error("session disconnected!");
			}
		});
	}
	
	/**
	 * 
	 */
	private void wakeup() {
		synchronized (this) {
			waiting = false;
			
		//	notifyAll();
		//	�� �������� wait ���¿� �ִ� ������� �׻� 1�� ��.
			notify();
		}
	}
	
	/**
	 * 
	 * @param address
	 * @param port
	 * @return
	 */
	public boolean connect(String address, int port) {
		synchronized (this) {
			log.info("connect server: '{}:{}'", address, port);
			return xaSession.connectServer(address, port);
		}
	}
	
	/**
	 * auto connect
	 * @return
	 */
	public boolean connect() {
		synchronized (this) {
			return connect(Settings.getServerIP(), Settings.getServerPort());
		}
	}
	
	/**
	 * 
	 */
	public void disconnect() {
		synchronized (this) {
			xaSession.disconnectServer();
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isConnected() {
		return xaSession.isConnected();
	}
	
	/**
	 * login
	 * @param id
	 * @param passwd
	 * @param certPasswd
	 * @return
	 */
	public boolean login(String id, String passwd, String certPasswd) {
		synchronized (this) {
			if (waiting) {
				return false;
			}
			
			// callback ������ ��ٸ��� �ִ� ����
			waiting = true;
			
			// COM login
			if (xaSession.login(id, passwd, certPasswd, 0, false)) {
				log.info("login requested: ID: {}", id);
				try {
					// login callback timeout: 5sec.
					wait(5000);
				}
				catch (InterruptedException ie) {
					throw new SessionException("login timeout", ie);
				}
				
				// thread resume
				return isLogined();
			}
			else {
				return false;
			}
		}
	}
	
	/**
	 * auto login
	 * @return
	 */
	public boolean login() {
		synchronized (this) {
			return login(Settings.getUserID(), Settings.getUserPasswd(), Settings.getUserCertPasswd());
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean logout() {
		synchronized (this) {
			logined = false;
			return xaSession.logout();
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isLogined() {
		return logined;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getLastError() {
		return xaSession.getLastError();
	}
	
	/**
	 * 
	 * @param error
	 * @return
	 */
	public String getErrorMessage(int error) {
		return xaSession.getErrorMessage(error);
	}
	
	
	//--------------------------------------------------------------------------
	// account
	//--------------------------------------------------------------------------
	
	/**
	 * 
	 * @return
	 */
	public List<Account> getAccountList() {
		List<Account> accList = new ArrayList<>();
		Account acc;
		
		int cnt = xaSession.getAccountListCount();
		for (int i = 0; i < cnt; i++) {
			String accNo = xaSession.getAccountList(i);
			
			acc = new Account();
			acc.setAccNo(accNo);
			acc.setAccName(xaSession.getAccountName(accNo));
			acc.setAccNickname(xaSession.getAcctNickname(accNo));
			acc.setAccDetail(xaSession.getAcctDetailName(accNo));
			
			accList.add(acc);
		}
		
		return accList;
	}
}