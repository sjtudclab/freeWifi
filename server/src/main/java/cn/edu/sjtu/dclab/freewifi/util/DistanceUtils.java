package cn.edu.sjtu.dclab.freewifi.util;

public class DistanceUtils {
	private static final double PI = Math.PI; // 圆周率
	private static final double R = 6371229; // 地球的半径

	public static double getDistance(double longt1, double lat1, double longt2,
			double lat2) {
		double x, y, distance;
		x = (longt2 - longt1) * PI * R
				* Math.cos(((lat1 + lat2) / 2) * PI / 180) / 180;
		y = (lat2 - lat1) * PI * R / 180;
		distance = Math.hypot(x, y);
		return distance;
	}

	public static double getLongitude(double longt1, double lat1, double distance) {
		double a = (180 * distance) / (PI * R * Math.cos(lat1 * PI / 180));
		return a;
	}

	public static double getLatitude(double longt1, double lat1, double distance) {
		double a = (180 * distance) / (PI * R * Math.cos(lat1 * PI / 180));
		return a;
	}

	public static void main(String[] args) {
		double s = DistanceUtils.getDistance(112.0235, 23.2563, 110.1235, 20.3563);
		System.out.println(s);

		double longt = DistanceUtils.getLongitude(112.0235, 23.2563, 377452);
		System.out.println(longt);
		double lat = DistanceUtils.getLatitude(112.0235, 23.2563, 377452);
		System.out.println(lat);
	}
}
