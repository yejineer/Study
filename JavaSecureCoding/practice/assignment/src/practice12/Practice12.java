// ��ǻ���а� 20170964 �̿���
package practice12;

public class Practice12 implements MyInterface{

	public void calculate(int x, int y) {
		System.out.println("����: " + (x+y));
		System.out.println("����: " + (x-y));
		System.out.println("����: " + (x*y));
		System.out.println("������: " + (x/y));
	}
	
	public static void main(String[] args) {
		MyInterface hello = new Practice12();
		hello.calculate(10, 20);
	}

}
