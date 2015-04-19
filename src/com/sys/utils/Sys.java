package com.sys.utils;

public class Sys {
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
