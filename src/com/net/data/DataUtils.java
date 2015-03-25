package com.net.data;

import java.util.HashMap;
import java.util.Map;

public class DataUtils {
	public static final String folder_name = "kikbug";
	public static final String url = "http://10.0.1.103:8080/FM.Android.Server/";
	public static final String urlPrefix = "http://10.0.1.103:8080/FM.Android.Server/apks/";
	private static Map<Long, String> id_paths = new HashMap<Long, String>();

	public static void put(long id, String path) {
		if (id_paths.containsKey(id)) {
			id_paths.remove(id);
		}
		id_paths.put(id, path);
	}

	public static String getPath(long id) {
		return id_paths.get(id);
	}
}
