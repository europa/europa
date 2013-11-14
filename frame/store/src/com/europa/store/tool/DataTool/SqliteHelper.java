package com.europa.store.tool.DataTool;

import com.europa.store.tool.MyLog;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SqliteHelper {
	public String TAG=this.getClass().getSimpleName();
	MyLog log=new MyLog(TAG);
	protected SQLiteDatabase database;
	private MySqliteHelper mySqliteHelper;
	
	public SqliteHelper(Context context) {
		mySqliteHelper=new MySqliteHelper(context);
	}
	
	/**
	 * 
	 * @param mode:0,readable;1,writable
	 */
	public void open(int mode){
		if(mode==0){
			database=mySqliteHelper.getReadableDatabase();
		}else{
			database=mySqliteHelper.getWritableDatabase();
		}
	}
	
	public void close(){
		mySqliteHelper.close();
	}
	
	public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String 
			 groupBy, String having, String orderBy){
		open(0);
		Cursor cursor=database.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
		return cursor;
	}
}
