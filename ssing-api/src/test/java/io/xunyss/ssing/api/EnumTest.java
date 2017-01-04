package io.xunyss.ssing.api;

import io.xunyss.ssing.api.tr.FieldType;

public class EnumTest {
	
	public static void main(String[] args) {
		System.out.println("...");
		
		FieldType f = FieldType.valueOf("1");
		System.out.println(f);
	}
}
