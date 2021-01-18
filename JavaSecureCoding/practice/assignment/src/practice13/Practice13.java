// ÄÄÇ»ÅÍÇÐ°ú 20170964 ÀÌ¿¹Áø
package practice13;

public class Practice13 {

	public static void main(String[] args) {
		MyInterface hello = new MyInterface() {
			public void calculate(int x, int y) {
				System.out.println("µ¡¼À: " + (x+y));
				System.out.println("»¬¼À: " + (x-y));
				System.out.println("°ö¼À: " + (x*y));
				System.out.println("³ª´°¼À: " + (x/y));
			}
		};
		
		hello.calculate(10, 20);
	}

}
