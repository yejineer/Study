
public class Practice12 implements MyInterface{

	public void calculate(int x, int y) {
		System.out.println("����: " + (x+y));
		System.out.println("����: " + (x-y));
		System.out.println("����: " + (x*y));
		System.out.println("������: " + (x/y));
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MyInterface hello = new Practice12();
		hello.calculate(10, 20);
	}

}
