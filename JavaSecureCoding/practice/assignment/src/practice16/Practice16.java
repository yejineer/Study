// ��ǻ���а� 20170964 �̿���
package practice16;

import java.lang.reflect.*;

public class Practice16 {

	public static void main(String[] args) throws ClassNotFoundException {

		// Ŭ���� �̸� ���
		Class<?> cls = Class.forName(args[0]);
		System.out.println("Ŭ���� �̸� : " + cls.getName());

		// Ŭ���� �Ҽ� ������ ����
		System.out.println("\n[�Ҽ� ����]");
		Field flist[] = cls.getDeclaredFields();

		for (Field fld : flist) {
			System.out.println("-----");
			System.out.println("�̸� = " + fld.getName());
			System.out.println("Ÿ�� = " + fld.getType());
			int mod = fld.getModifiers();
			System.out.println("���� ������ = " + Modifier.toString(mod));
		}

		// Ŭ���� �Ҽ� �޼ҵ� �ñ״�ó
		System.out.println("\n[�Ҽ� �޼ҵ��� �޼ҵ� �ñ״�ó]");
		Method mlist[] = cls.getDeclaredMethods();
		for (Method m : mlist) {
			System.out.println(m.toString());
		}

		// Ŭ���� ������ �ñ״�ó
		System.out.println("\n[������ �ñ״�ó]");
		Constructor<?> ctorlist[] = cls.getDeclaredConstructors();
		for (Constructor<?> c : ctorlist) {
			System.out.println(c.toString());
		}
	}

}