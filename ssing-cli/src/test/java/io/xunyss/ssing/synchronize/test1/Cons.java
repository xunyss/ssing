package io.xunyss.ssing.synchronize.test1;

import java.util.Random;

class Cons extends Thread { // 소비자 스레드 클래스
	Buff buff; // 데이터를 추출할 버퍼
	String name; // 소비자 스레드 이름
	Random random = new Random(); // 임의시간 생성 객체

	public Cons(Buff b, String nm) { // 생성자
		buff = b; // 버퍼 객체 초기화
		name = nm; // 소비자 스레드 이름
	}

	public void run() {
		for (int i = 0; i < 6; i++) {
			String data = buff.get(); // 버퍼에서 데이터 추출
			System.out.println(name + " : " + data); // 추출된 데이터 화면출력
			try {
				Thread.sleep(random.nextInt(1000)); // 1초 이하 임의의 기간 동안 잠자기
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
