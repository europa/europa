package com.europa.filehelper.bean;

import java.io.File;

public class FileItem {
	int type;
	File file;
	Boolean checked = false;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File fileName) {
		this.file = fileName;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
}
