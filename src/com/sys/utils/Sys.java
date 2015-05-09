package com.sys.utils;

import android.content.Context;
import android.util.DisplayMetrics;

public class Sys {
	public static String getRelease() {
		return android.os.Build.VERSION.RELEASE;
	}

	public static String getModel() {
		return android.os.Build.MODEL;
	}

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
