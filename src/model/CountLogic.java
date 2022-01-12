package model;

public class CountLogic {


	private static int x=0;



	public static void CountUp() {
		x++;
	}



	public static int getX() {
		return x;
	}



	public static void setX(int x) {
		CountLogic.x = x;
	}



}
