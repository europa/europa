package com.europa.filehelper.ui.adapter;

import java.util.List;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.europa.filehelper.R;
import com.europa.filehelper.Tool.BitmapTool;
import com.europa.filehelper.Tool.GlobalValue;
import com.europa.filehelper.bean.FileItem;
import com.europa.filehelper.ui.activity.BaseActivity;
import com.europa.tool.ToolAdapter;

public class FileApdater extends ToolAdapter<FileItem>{

	LayoutInflater mInflater;
	ViewHolder holder;
	BaseActivity hostActivity;
	public FileApdater(List<FileItem> list, Activity activity) {
		super(list, activity);
		hostActivity=(BaseActivity) activity;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		if (mInflater == null) {
			mInflater = activity.getLayoutInflater();
		}
		if (arg1 == null) {
			arg1 = mInflater.inflate(R.layout.item_file, null);
			holder = new ViewHolder();
			holder.typeImg = (ImageView) arg1.findViewById(R.id.typeImg);
			holder.fileNameText = (TextView) arg1
					.findViewById(R.id.fileNameText);
			holder.fileChk = (CheckBox) arg1.findViewById(R.id.fileChk);
			arg1.setTag(holder);
		} else {
			holder = (ViewHolder) arg1.getTag();
		}
		FileItem item = getItem(arg0);
		if (item.getType() == R.drawable.image) {
			holder.typeImg.setImageBitmap(BitmapTool.decodeSampledBitmapFromFile(item.getFile(),40, 40));
		}
		holder.typeImg.setBackgroundResource(item.getType());
		holder.fileNameText.setText(item.getFile().getName());
		holder.fileChk.setVisibility(hostActivity.edited ? View.VISIBLE
				: View.INVISIBLE);
		if (hostActivity.edited) {
			holder.fileChk.setChecked(item.getChecked());
		}
		return arg1;
	}
	
	class ViewHolder{
		ImageView typeImg;
		TextView fileNameText;
		CheckBox fileChk;
	}
}
