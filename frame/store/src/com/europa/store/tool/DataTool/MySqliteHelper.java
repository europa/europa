package com.europa.store.tool.DataTool;

import com.europa.store.tool.SqlValue;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqliteHelper extends SQLiteOpenHelper {

	
	public MySqliteHelper(Context context){
		super(context, SqlValue.DATABASE_NAME, null, SqlValue.DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		arg0.execSQL(SqlValue.USER_CREAT);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		arg0.execSQL("DROP TABLE IF EXISTS "+SqlValue.TABLE_USER);
		onCreate(arg0);
	}

}
