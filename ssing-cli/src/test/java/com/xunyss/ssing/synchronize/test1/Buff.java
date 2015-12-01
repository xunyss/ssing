package com.xunyss.ssing.synchronize.test1;

class Buff { // ������ �Һ��� ������ ������ ����
	private boolean flag; // ���ۿ� �����Ͱ� �ִ°�?(������ true)
	private String data; // ������ ����(�����ʹ� String)

	public Buff() {
		flag = false;
	} // ������ �÷��� �ʱ�ȭ(������ ����)

	synchronized void put(String st) { // ���ۿ� ������ ����, synchronized �� ������ ��ȣ����
		while (flag == true) { // �����Ͱ� ������(�Һ��ڰ� �����͸� ������� �ʾ�����)
			try {
				wait(); // ��ٸ�
			} catch (Exception e) { // ����ó��
				e.printStackTrace();
			}
		}
		data = st; // ���ο� �����͸� ���ۿ� ����
		flag = true; // ������ ���ۿ� ���� ǥ��
		notifyAll(); // ��ٸ��� ������ ��� ����
	}

	synchronized String get() { // �޼ҵ带 synchronized �� ����ȭ(�� ������ �� �����常 ���ٰ���)
		while (flag == false) { // �����Ͱ� ���ۿ� ������
			try {
				wait(); // ��ٸ�
			} catch (Exception e) { // ����ó��
				e.printStackTrace();
			}
		}
		String ret = data; // ���ۿ��� ������ ����
		flag = false; // ���ۿ� ������ ���� ǥ��
		notifyAll(); // ��ٸ��� ��� �����带 ����

		return ret; // ������ ����
	}
}
