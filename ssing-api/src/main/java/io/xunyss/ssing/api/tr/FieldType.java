package io.xunyss.ssing.api.tr;

/**
 * 
 * @author XUNYSS
 */
public enum FieldType {
	CHAR,
	LONG,
	DOUBLE;
	
	public static FieldType valueOfCode(String code) {
		switch (code) {
		case "1":
			return CHAR;
		case "2":
			return LONG;
		case "3":
			return DOUBLE;
		}
		return null;
	}
}
