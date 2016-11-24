package io.xunyss.ssing.synchronize.test1;

import java.util.Random;

class Proc extends Thread { // 생산자 스레드 클래스
	Buff buff; // 데이터를 저장할 버퍼
	String name; // 스레드 이름
	Random random = new Random(); // 임의 시간 생성 객체

	public Proc(Buff b, String nm) {
		buff = b; // 데이터를 저장할 버퍼
		name = nm; // 생산자 이름
	}

	public void run() {
		for (int i = 0; i < 4; i++) { // 데이터를 4회 생성하여 버퍼에 저장
			String tmp = name + "-" + i; // 저장할 String 데이터
			buff.put(tmp); // 버퍼에 데이터 저장
			System.out.println(name + " : " + tmp);
			try {
				Thread.sleep(random.nextInt(1000)); // 1초 이하 임의의 기간 동안 잠자기
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
