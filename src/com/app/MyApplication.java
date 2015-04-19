package com.app;

import com.local.data.UserInfo;

import android.app.Application;

public class MyApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		UserInfo.getInstance().init(getApplicationContext());
	}
}
