package com.xunyss.ssing.synchronize.test1;

class Buff { // 생산자 소비자 사이의 데이터 버퍼
	private boolean flag; // 버퍼에 데이터가 있는가?(있으면 true)
	private String data; // 데이터 버퍼(데이터는 String)

	public Buff() {
		flag = false;
	} // 데이터 플래그 초기화(데이터 없음)

	synchronized void put(String st) { // 버퍼에 데이터 저장, synchronized 로 스레드 상호배제
		while (flag == true) { // 데이터가 있으면(소비자가 데이터를 사용하지 않았으면)
			try {
				wait(); // 기다림
			} catch (Exception e) { // 예외처리
				e.printStackTrace();
			}
		}
		data = st; // 새로운 데이터를 버퍼에 저장
		flag = true; // 데이터 버퍼에 있음 표시
		notifyAll(); // 기다리는 스레드 모두 깨움
	}

	synchronized String get() { // 메소드를 synchronized 로 동기화(한 순간에 한 스레드만 접근가능)
		while (flag == false) { // 데이터가 버퍼에 없으면
			try {
				wait(); // 기다림
			} catch (Exception e) { // 예외처리
				e.printStackTrace();
			}
		}
		String ret = data; // 버퍼에서 데이터 추출
		flag = false; // 버퍼에 데이터 없음 표시
		notifyAll(); // 기다리는 모든 스레드를 깨움

		return ret; // 데이터 리턴
	}
}
