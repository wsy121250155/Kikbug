package com.app;

import com.local.data.UserInfo;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;

public class MyApplication extends Application {
	private static String resolution;

	public static String getResolution() {
		return resolution;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		// 清空上次的登陆信息
		UserInfo.getInstance().setToken(null);
		UserInfo.getInstance().setSsid(0);
		UserInfo.getInstance().setTimeOut(0);

		UserInfo.getInstance().init(getApplicationContext());
		resolution = getDisplayMetrics(getApplicationContext());
	}

	private static String getDisplayMetrics(Context cx) {
		DisplayMetrics dm = new DisplayMetrics();
		dm = cx.getApplicationContext().getResources().getDisplayMetrics();
		int screenWidth = dm.widthPixels;
		int screenHeight = dm.heightPixels;
		// float density = dm.density;
		// float xdpi = dm.xdpi;
		// float ydpi = dm.ydpi;
		return screenWidth + "," + screenHeight;
	}
}
