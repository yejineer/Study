// ��ǻ���а� 20170964 �̿���

import java.util.Scanner;

public class CCLoader extends ClassLoader {
	
	public CCLoader(ClassLoader classLoader) {
		super(classLoader);
	}
	
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		Scanner scan = new Scanner(System.in);
		System.out.print("����� �̸��� �Է��ϼ���: ");
		String username = scan.nextLine();
		System.out.print("�н����带 �Է��ϼ���: ");
		String password = scan.nextLine();
		
		try {
			if (!username.equals("dongduk") || !password.equals("1234")) {
				throw new Exception();
			}
			System.out.println("Loading Class \"" + name + "\"");
			return super.loadClass(name);		
		} catch (Exception e) {
			System.out.println("����� ���̵� Ȥ�� ����� ��ġ���� �ʽ��ϴ�.");
			return null;
		}
	}
}