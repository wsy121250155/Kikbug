package com.view.util;

import java.io.IOException;

import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class SysCall {
	public static void bumpSoftInput(EditText rootView, Context context) {
		rootView.setFocusable(true);
		rootView.setFocusableInTouchMode(true);
		rootView.requestFocus();
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(rootView, 0);
	}

	public static void hideSoftInput(View rootView, Context context) {
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(rootView.getWindowToken(), 0);
	}

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
