package com.xunyss.ssing.synchronize.test1;

public class ProCon_Ex {
	public static void main(String[] args) throws Exception {
		Buff buf = new Buff();
		
		Proc proc1 = new Proc(buf, "Proc1"); // ������ 1
		Proc proc2 = new Proc(buf, "Proc2"); // ������ 2
		Proc proc3 = new Proc(buf, "Proc3"); // ������ 3
		
		Cons cons1 = new Cons(buf, "Cons1"); // �Һ��� 1
		Cons cons2 = new Cons(buf, "Cons2"); // �Һ��� 2

		proc1.start(); // ������ 1 ������ ����
		proc2.start(); // ������ 2 ������ ����
		proc3.start(); // ������ 3 ������ ����
		
		cons1.start(); // �Һ��� 1 ������ ����
		cons2.start(); // �Һ��� 2 ������ ����
	}
}
