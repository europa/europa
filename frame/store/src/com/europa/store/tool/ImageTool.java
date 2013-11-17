package com.europa.store.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageTool {
	public static Bitmap readBitMap(Context context, String path) {
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		opt.inJustDecodeBounds = false;
		// 获取资源图片
		File file=new File(path);
		InputStream is=null;
		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Bitmap bitmap=BitmapFactory.decodeStream(is, null, opt);
		bitmap=Bitmap.createScaledBitmap(bitmap,300,300, false);
		return bitmap;
	}
}
