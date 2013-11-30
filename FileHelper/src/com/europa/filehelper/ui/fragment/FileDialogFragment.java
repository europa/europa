package com.europa.filehelper.ui.fragment;

import com.europa.filehelper.ui.activity.MainActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;

public class FileDialogFragment extends DialogFragment {

	public static String mTitle="";
	MainActivity hostActivity;
	public static FileDialogFragment newInstance(String title) {
		FileDialogFragment fileDialogFragment=new FileDialogFragment();
		mTitle=title;
		return fileDialogFragment;
	}
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		hostActivity=(MainActivity) getActivity();
		return new AlertDialog.Builder(hostActivity).setTitle(mTitle).setNegativeButton("否",new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				hostActivity.clickNegativeBtn();
			}
		}).setPositiveButton("是", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				hostActivity.clickPositiveBtn();
			}
		}).create();
	}
	
}
