// 컴퓨터학과 20170964 이예진
package practice16;

import java.lang.reflect.*;

public class Practice16 {

	public static void main(String[] args) throws ClassNotFoundException {

		// 클래스 이름 출력
		Class<?> cls = Class.forName(args[0]);
		System.out.println("클래스 이름 : " + cls.getName());

		// 클래스 소속 변수들 정보
		System.out.println("\n[소속 변수]");
		Field flist[] = cls.getDeclaredFields();

		for (Field fld : flist) {
			System.out.println("-----");
			System.out.println("이름 = " + fld.getName());
			System.out.println("타입 = " + fld.getType());
			int mod = fld.getModifiers();
			System.out.println("접근 제어자 = " + Modifier.toString(mod));
		}

		// 클래스 소속 메소드 시그니처
		System.out.println("\n[소속 메소드의 메소드 시그니처]");
		Method mlist[] = cls.getDeclaredMethods();
		for (Method m : mlist) {
			System.out.println(m.toString());
		}

		// 클래스 생성자 시그니처
		System.out.println("\n[생성자 시그니처]");
		Constructor<?> ctorlist[] = cls.getDeclaredConstructors();
		for (Constructor<?> c : ctorlist) {
			System.out.println(c.toString());
		}
	}

}
