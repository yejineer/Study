// ��ǻ���а� 20170964 �̿���
package practice13;

public class Practice13 {

	public static void main(String[] args) {
		MyInterface hello = new MyInterface() {
			public void calculate(int x, int y) {
				System.out.println("����: " + (x+y));
				System.out.println("����: " + (x-y));
				System.out.println("����: " + (x*y));
				System.out.println("������: " + (x/y));
			}
		};
		
		hello.calculate(10, 20);
	}

}
