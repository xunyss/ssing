package io.xunyss.ssing.synchronize.test1;

import java.util.Random;

class Proc extends Thread { // ������ ������ Ŭ����
	Buff buff; // �����͸� ������ ����
	String name; // ������ �̸�
	Random random = new Random(); // ���� �ð� ���� ��ü

	public Proc(Buff b, String nm) {
		buff = b; // �����͸� ������ ����
		name = nm; // ������ �̸�
	}

	public void run() {
		for (int i = 0; i < 4; i++) { // �����͸� 4ȸ �����Ͽ� ���ۿ� ����
			String tmp = name + "-" + i; // ������ String ������
			buff.put(tmp); // ���ۿ� ������ ����
			System.out.println(name + " : " + tmp);
			try {
				Thread.sleep(random.nextInt(1000)); // 1�� ���� ������ �Ⱓ ���� ���ڱ�
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
