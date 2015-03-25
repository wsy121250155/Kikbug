package com.view.util;

import java.io.IOException;

import android.util.Log;
import android.view.KeyEvent;

public class SysCall {
	public static void logi(String tag, String info) {
		Log.i(tag, info);
	}

	public static void logi(String tag, int info) {
		Log.i(tag, "" + info);
	}

	public static void clickBack() {
		try {
			Runtime runtime = Runtime.getRuntime();
			runtime.exec("input keyevent " + KeyEvent.KEYCODE_BACK);
		} catch (IOException e) {
			// Log.e("Exception when doBack", e.toString());
			e.printStackTrace();
		}
	}
}
