package com.europa.filehelper.Tool;

import java.io.File;

import android.os.Environment;

public class Brain {
	File currentFile=Environment.getExternalStorageDirectory();
	private static Brain brain;
	private Brain(){}
	public static Brain newInstance(){
		if(brain==null){
			brain=new Brain();
		}
		return brain;
	}
	public File getCurrentFile() {
		return currentFile;
	}
	public void setCurrentFile(String currentFile) {
		this.currentFile = new File(currentFile);
	}
	
	public void setCurrentFile(File file){
		this.currentFile=file;
	}
}
