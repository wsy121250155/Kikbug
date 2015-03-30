package com.net.data;

import java.io.File;

import android.app.DownloadManager;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;

import com.local.data.ContentUtils;
import com.local.data.FileUtils;
import com.sys.utils.Sys;

public class DownTask {
	public static final String FILESIZE = "fileSize";
	public static final String DOWNSIZE = "bytesDL";
	private Context context;
	private String appName;
	private String apkUrl;
	private String folderName;
	private String savePath;
	private DownloadManager downloadManager;
	private DownloadChangeObserver observer;
	private long downloadId;
	private Handler handler;

	public DownTask(Context context, Handler handler, String appName) {
		this.context = context;
		this.appName = appName;
		this.handler = handler;
		if (!appName.endsWith(".apk")) {
			Sys.error("apk name error");
		}
		apkUrl = DataUtils.urlPrefix + appName;
		folderName = FileUtils.isFolderExist(DataUtils.folder_name);
		savePath = folderName + "/" + appName;
		observer = new DownloadChangeObserver(null);
		downloadManager = (DownloadManager) context
				.getSystemService(Context.DOWNLOAD_SERVICE);
	}

	public void quit() {
		downloadManager.remove(downloadId);
	}

	public void down() {
		// check file
		File f = new File(savePath);
		if (f.exists())
			f.delete();
		DownloadManager.Request request = new DownloadManager.Request(
				Uri.parse(apkUrl));
		request.setDestinationInExternalPublicDir(DataUtils.folder_name,
				appName);
		request.allowScanningByMediaScanner();// 表示允许MediaScanner扫描到这个文件，默认不允许。
		request.setTitle("程序更新");// 设置下载中通知栏提示的标题
		request.setDescription("程序更新正在下载中:" + folderName);// 设置下载中通知栏提示的介绍
		request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
		downloadId = downloadManager.enqueue(request);
		// 注册
		DataUtils.put(downloadId, savePath);
		context.getContentResolver().registerContentObserver(
				ContentUtils.CONTENT_URI, true, observer);
	}

	class DownloadChangeObserver extends ContentObserver {
		public DownloadChangeObserver(Handler handler) {
			super(handler);
		}

		@Override
		public void onChange(boolean selfChange) {
			DownloadManager.Query query = new DownloadManager.Query();
			query.setFilterById(downloadId);
			Cursor c = downloadManager.query(query);
			if (c != null && c.moveToFirst()) {
				int fileSizeIdx = c
						.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES);
				int bytesDLIdx = c
						.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR);
				int fileSize = c.getInt(fileSizeIdx);
				int bytesDL = c.getInt(bytesDLIdx);
				if (null != handler && fileSize != 0) {
					Message message = new Message();
					message.getData().putInt(FILESIZE, fileSize);
					message.getData().putInt(DOWNSIZE, bytesDL);
					handler.sendMessage(message);
				}
			}
			c.close();
		}
	}
}
