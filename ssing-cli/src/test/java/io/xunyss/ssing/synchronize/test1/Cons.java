package io.xunyss.ssing.synchronize.test1;

import java.util.Random;

class Cons extends Thread { // �Һ��� ������ Ŭ����
	Buff buff; // �����͸� ������ ����
	String name; // �Һ��� ������ �̸�
	Random random = new Random(); // ���ǽð� ���� ��ü

	public Cons(Buff b, String nm) { // ������
		buff = b; // ���� ��ü �ʱ�ȭ
		name = nm; // �Һ��� ������ �̸�
	}

	public void run() {
		for (int i = 0; i < 6; i++) {
			String data = buff.get(); // ���ۿ��� ������ ����
			System.out.println(name + " : " + data); // ����� ������ ȭ�����
			try {
				Thread.sleep(random.nextInt(1000)); // 1�� ���� ������ �Ⱓ ���� ���ڱ�
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
