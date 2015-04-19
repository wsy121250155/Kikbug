package com.view.util;

import java.io.IOException;

import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

public class SysCall {
	public static void toast(Context context, String info) {
		Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
	}

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
