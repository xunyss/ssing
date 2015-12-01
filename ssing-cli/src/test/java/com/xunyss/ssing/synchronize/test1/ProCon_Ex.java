package com.xunyss.ssing.synchronize.test1;

public class ProCon_Ex {
	public static void main(String[] args) throws Exception {
		Buff buf = new Buff();
		
		Proc proc1 = new Proc(buf, "Proc1"); // 생산자 1
		Proc proc2 = new Proc(buf, "Proc2"); // 생산자 2
		Proc proc3 = new Proc(buf, "Proc3"); // 생산자 3
		
		Cons cons1 = new Cons(buf, "Cons1"); // 소비자 1
		Cons cons2 = new Cons(buf, "Cons2"); // 소비자 2

		proc1.start(); // 생산자 1 스레드 시작
		proc2.start(); // 생산자 2 스레드 시작
		proc3.start(); // 생산자 3 스레드 시작
		
		cons1.start(); // 소비자 1 스레드 시작
		cons2.start(); // 소비자 2 스레드 시작
	}
}
