package com.sys.utils;

public class Sys {
	// 操作系统版本号
	public static String getRelease() {
		return android.os.Build.VERSION.RELEASE;
	}

	// 获取手机的型号 设备名称
	public static String getModel() {
		return android.os.Build.MODEL;
	}

	// 厂商
	public static String getManufacturer() {
		return android.os.Build.MANUFACTURER;
	}

	public static void error(String info) {
		try {
			throw new Exception(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static long getTime() {
		return System.currentTimeMillis();
	}
}
