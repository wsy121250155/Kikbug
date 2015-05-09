package com.local.data;

import java.io.File;

import android.os.Environment;

public class FileUtils {
	public static String isFolderExist(String dir) {
		File folder = Environment.getExternalStoragePublicDirectory(dir);
		boolean rs = (folder.exists() && folder.isDirectory()) ? true : folder
				.mkdirs();
		return folder.getAbsolutePath();
	}

	public static String getNameFromPath(String path) {
		String[] parts = path.split("/");
		return parts[parts.length - 1];
	}
}
