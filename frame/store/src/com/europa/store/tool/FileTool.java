package com.europa.store.tool;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public class FileTool {
	public static final String getPath(Context context, Uri uri) {
		if ("content".equalsIgnoreCase(uri.getScheme())) {
			String[] projection = { "_data" };
			Cursor cursor = null;
			cursor = context.getContentResolver().query(uri, projection,
					null, null, null);
			int column_index=cursor.getColumnIndexOrThrow("_data");
			if(cursor.moveToFirst()){
				return cursor.getString(column_index);
			}
		}else if("file".equalsIgnoreCase(uri.getScheme())){
			return uri.getPath();
		}
		return null;

	}
}
