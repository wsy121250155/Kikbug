package com.sys.utils;

public class Sys {
	public static void error(String info) {
		try {
			throw new Exception(info);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
