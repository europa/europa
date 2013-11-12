package com.europa.store.tool;

import android.content.Context;
import android.widget.Toast;

public class ToastTool {
	/**
	 * 
	 * @param context
	 * @param toast:the message to toast
	 */
	public static void show(Context context,String toast){
		Toast.makeText(context,toast,1000).show();
	}
}
