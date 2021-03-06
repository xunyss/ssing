package io.xunyss.ssing.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author XUNYSS
 */
public class Settings {
	
	private static final Logger log = LoggerFactory.getLogger(Settings.class);
	
	private static Properties prop = null;
	
	static {
		String ssingSettingFile = System.getProperty("ssing.setting.file",
				"./ssing.properties");		// default..
		
		InputStream inStream = null;
		try {
			File file = new File(ssingSettingFile);
			if (file.isFile()) {
				inStream = new FileInputStream(file);
				prop = new Properties();
				prop.load(inStream);
				
				log.info("setting file({}) is loaded", ssingSettingFile);
			}
			else {
				log.info("setting file({}) is not exist", ssingSettingFile);
			}
		}
		catch (IOException ioe) {
			log.error("fail to load '{}'", ssingSettingFile);
		}
		finally {
			if (inStream != null) {
				try {
					inStream.close();
				} catch (IOException ignored) {}
			}
		}
	}
	
	private static String get(String key, String defaultValue) {
		if (prop == null) {
			return defaultValue;
		}
		return prop.getProperty(key, defaultValue);
	}
	private static String get(String key) {
		return get(key, null);
	}
	private static int getInt(String key, int defaultValue) {
		String value = get(key);
		return value != null ? Integer.parseInt(value) : defaultValue;
	}
//	private static int getInt(String key) {
//		return getInt(key, 0);
//	}
	
	
	//--------------------------------------------------------------------------
	
	/**
	 * 
	 * @return
	 */
	static long getLoginTimeout() {
		return getInt("ssing.login.timeout",
				5000);						// default
	}
	
	/**
	 * 
	 * @return
	 */
	static String getServerIP() {
		return get("ssing.server.ip",
				"hts.ebestsec.co.kr");		// default
	}
	
	/**
	 * 
	 * @return
	 */
	static int getServerPort() {
		return getInt("ssing.server.port",
				20001);						// default
	}
	
	/**
	 * 
	 * @return
	 */
	static String getUserID() {
		return get("ssing.user.id");
	}
	
	/**
	 * 
	 * @return
	 */
	static String getUserPasswd() {
		return get("ssing.user.passwd");
	}
	
	/**
	 * 
	 * @return
	 */
	static String getUserCertPasswd() {
		return get("ssing.user.certpasswd");
	}
}
