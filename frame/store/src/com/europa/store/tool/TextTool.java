package com.europa.store.tool;

import android.text.TextUtils;
import android.widget.TextView;

public class TextTool {
	/**
	 * 
	 * @param text:the textview to be checked if it's a style of email.
	 * @return
	 */
	public static Boolean isEmail(TextView text){
		if(text.getText().toString().trim().contains("@")){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 
	 * 
	 * @param text:the str in the text
	 * @return String:the returned str has bean trimed.
	 */
	public static final String getStr(TextView text){
		return text.getText().toString().trim();
	}
	
	/**
	 * 
	 * @param textView:to be checked if it's empty.
	 * Attentions!the text in the textView is trimed.
	 * @return true,is empty;false,is not empty.
	 */
	public static final Boolean isEmpty(TextView textView){
		if(TextUtils.isEmpty(textView.getText().toString().trim())){
			return true;
		}else{
			return false;
		}
	}
}
