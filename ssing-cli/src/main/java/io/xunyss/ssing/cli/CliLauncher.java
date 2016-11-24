package io.xunyss.ssing.cli;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import io.xunyss.commons.Utils;
import io.xunyss.ssing.Query;
import io.xunyss.ssing.Real;
import io.xunyss.ssing.Response;
import io.xunyss.ssing.Session;
import jline.console.ConsoleReader;

/**
 * 
 * @author XUNYSS
 */
public class CliLauncher {
	
	public static void main(String[] args) throws IOException {
		new CliLauncher().run();
	}
	
	private ConsoleReader console = null;
	
	public CliLauncher() {
		try {
			console = new ConsoleReader();
		}
		catch (IOException ioe) {
			System.err.println("console reader creation fail.");
			System.exit(-1);
		}
	}
	
	private void run() throws IOException {
		if (login()) {
			console.println("login success.");
			console.setPrompt("real> ");
			
			processCommand();
		}
	}
	
	private boolean login() throws IOException {
//		String username = null, password = null, certpass = null;
//		
//		do {
//			username = console.readLine("enter username: ");
//		} while (Utils.isEmpty(username));
//		
//		do {
//			password = console.readLine("enter password: ", '*');
//		} while (Utils.isEmpty(password));
//
//		do {
//			certpass = console.readLine("enter certpass: ", '*');
//		} while (Utils.isEmpty(certpass));
//		
//		return Session.getInstance()
//				.login(username, password, certpass);
		
		return Session.getInstance()
				.login("xuny", "password", "password");
	}
	
	private void processCommand() throws IOException {
		String line = null;
		
		while ((line = console.readLine()) != null) {
			if ("cls".equalsIgnoreCase(line)) {
				console.clearScreen();
			}
			else if ("exit".equalsIgnoreCase(line)) {
				Session.getInstance().logout();
				break;
			}
			else if (Utils.isEmpty(line)) {
				continue;
			}
			else {
				Method method = null;
				try {
					method = getClass().getMethod(line, String.class);
					method.invoke(this, line);
				}
				catch (NoSuchMethodException nsme) {
					console.println("알수 없는 명령: " + line);
				}
				catch (InvocationTargetException ite) {
					ite.printStackTrace();
					console.println("명령 수행중 에러 발생: " + ite.getMessage());
				}
				catch (IllegalAccessException iae) {
					iae.printStackTrace();
					console.println("명령 수행중 에러 발생: " + iae.getMessage());
				}
			}
		}
	}
	
	public void logout(String command) throws IOException {
		Session.getInstance().logout();
	}
	
	public void bbb(String command) throws IOException {
		Query query = Query.getInstance();
		Response res = query.t1101("000660");
		
		System.out.println("this is bbb\n" + res.f1 + " " + res.f2);
	}
	
	public void ccc(String command) throws IOException {
		Real real = Real.getInstance();
		real.now();
	}
	
	public void ddd(String command) throws IOException {
		Real real = Real.getInstance();
		real.stop();
	}
}
