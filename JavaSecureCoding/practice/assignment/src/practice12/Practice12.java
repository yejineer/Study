// ÄÄÇ»ÅÍÇÐ°ú 20170964 ÀÌ¿¹Áø
package practice12;

public class Practice12 implements MyInterface{

	public void calculate(int x, int y) {
		System.out.println("µ¡¼À: " + (x+y));
		System.out.println("»¬¼À: " + (x-y));
		System.out.println("°ö¼À: " + (x*y));
		System.out.println("³ª´°¼À: " + (x/y));
	}
	
	public static void main(String[] args) {
		MyInterface hello = new Practice12();
		hello.calculate(10, 20);
	}

}
