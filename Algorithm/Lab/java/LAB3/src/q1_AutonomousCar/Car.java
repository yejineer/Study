package q1_AutonomousCar;

import java.util.Scanner;

public class Car {

static int[][] map;
	
	public static float percentage = 0;
	static final int WALL = 1;
	static final int PATHWAY = 0;
	static final int MARKED = 2;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		map = new int[21][21];
		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map[i].length; j++)
				map[i][j] = 0;
		
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		float ePer = scan.nextFloat();
		float wPer = scan.nextFloat();
		float sPer = scan.nextFloat();
		float nPer = scan.nextFloat();
		
		
	}
	

	
	public static int getPer(int n, float ePer, float wPer, float sPer, float nPer) {
		
		return 1;
	}

}
