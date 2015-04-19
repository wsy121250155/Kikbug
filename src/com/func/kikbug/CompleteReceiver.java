package com.func.kikbug;

import java.io.File;

import com.local.data.AppConfig;
import com.net.data.ApkMaps;
import com.net.data.DataConfig;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

public class CompleteReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i("CompleteReceiver", "download over");
		Toast.makeText(context, "下载成功", AppConfig.HINT_TIME).show();
		long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
		String filePath = ApkMaps.getInstance().getPath(id);
		Log.i("CompleteReceiver", filePath);
		File _file = new File(filePath);
		if (!_file.exists()) {
			return;
		}
		Intent newIntent = new Intent();
		newIntent.setAction("android.intent.action.VIEW");// 向用户显示数据
		newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// 以新压入栈
		newIntent.addCategory("android.intent.category.DEFAULT");
		Uri abc = Uri.fromFile(_file);
		newIntent
				.setDataAndType(abc, "application/vnd.android.package-archive");
		context.startActivity(newIntent);
	}
}
