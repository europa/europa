package com.europa.store.tool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.os.Environment;
import android.util.Log;

public class MyLog {
	private static boolean isDebug = GlobalValue.DEBUG;
	// static {
	// if (true) {
	// isDebug = true;
	// } else {
	// isDebug = false;
	// }
	// }
	// 日志文件在sdcard中的路径
	private static String MYLOG_PATH_SDCARD_DIR = Environment
			.getExternalStorageDirectory().getPath()+File.pathSeparator+GlobalValue.APP_NAME+File.pathSeparator;
	// sd卡中日志文件的最多保存天数
	private static int SDCARD_LOG_FILE_SAVE_DAYS = 0;
	// 本类输出的日志文件名称
	private static String MYLOGFILEName =GlobalValue.APP_NAME;
	// 日志的输出格式
	private static SimpleDateFormat myLogSdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	// 日志文件格式
	private static SimpleDateFormat logfile = new SimpleDateFormat("yyyy-MM-dd");

	private String tag;

	public MyLog(String tag) {
		this.tag = tag;
	}

	public static MyLog getLog(String tag) {
		return new MyLog(tag);
	}

	public static void v(String tag, String msg) {
		if (isDebug) {
			Log.v(tag, msg);
		} else {
			writeLogtoFile(tag, msg, "Verbose");
		}
	}

	public static void d(String tag, String msg) {
		if (isDebug) {
			Log.d(tag, msg);
		} else {
			writeLogtoFile(tag, msg, "Debug");
		}
	}

	public static void i(String tag, String msg) {
		if (isDebug) {
			Log.i(tag, msg);
		} else {
			writeLogtoFile(tag, msg, "Infos");
		}
	}

	public static void w(String tag, String msg) {
		if (isDebug) {
			Log.w(tag, msg);
		} else {
			writeLogtoFile(tag, msg, "Warnning");
		}
	}

	public static void e(String tag, String msg) {
		if (isDebug) {
			Log.e(tag, msg);
		} else {
			writeLogtoFile(tag, msg, "Errors");
		}
	}
	public static void e(String tag, String msg,Throwable e) {
		if (isDebug) {
			Log.e(tag, msg);
		} else {
			writeLogtoFile(tag, msg, "Errors");
		}
	}
	
	public static void e(String tag,Throwable e){
		StringWriter stringWriter= new StringWriter();  
        PrintWriter writer= new PrintWriter(stringWriter);  
        e.printStackTrace(writer);  
        StringBuffer buffer= stringWriter.getBuffer();
		if(isDebug){
			Log.e(tag, buffer.toString());
		}else{
	        writeLogtoFile(tag, buffer.toString(), "Errors");
		}
	}

	public void v(String msg) {
		if (isDebug) {
			Log.v(tag, msg);
		} else {
			writeLogtoFile(tag, msg, "Verbose");
		}
	}

	public void d(String msg) {
		if (isDebug) {
			Log.d(tag, msg);
		} else {
			writeLogtoFile(tag, msg, "Debug");
		}
	}

	public void i(String msg) {
		if (isDebug) {
			Log.i(tag, msg);
		} else {
			writeLogtoFile(tag, msg, "Infos");
		}
	}

	public void w(String msg) {
		if (isDebug) {
			Log.w(tag, msg);
		} else {
			writeLogtoFile(tag, msg, "Warnning");
		}
	}

	public void e(String msg) {
		if (isDebug) {
			Log.e(tag, msg);
		} else {
			writeLogtoFile(tag, msg, "Errors");
		}
	}
	public void e(Throwable e){
		if (isDebug) {
			Log.e(tag, e.toString());
		} else {
			writeLogtoFile(tag, e.toString(), "Errors");
		}
	}

//	public void e(String msg, Throwable ex) {
//		if (isDebug) {
//			Log.e(tag, msg);
//			Log.e(tag, msg, ex);
//		} else {
//			writeLogtoFile(tag, msg, "Errors");
//			StackTraceElement[] elements = ex.getStackTrace();
//			if (elements != null) {
//				for (StackTraceElement item : elements) {
//					writeLogtoFile(tag,
//							item.getMethodName() + "@" + item.getLineNumber(),
//							"Errors");
//				}
//			}
//
//		}
//	}

	public void error(String msg, Throwable ex) {
		this.e(msg, ex);
	}

	public void error(Throwable ex) {
		this.e(ex.getMessage(), ex);
	}

	public void debug(String msg) {
		this.d(msg);
	}

	/**
	 * 打开日志文件并写入
	 * 
	 * @param tag日志标签
	 * @param text日志信息
	 * @param lever日志等级
	 */
	private static void writeLogtoFile(String tag, String text, String lever) {
		Date nowtime = new Date();
		String needWriteFiel = logfile.format(nowtime);
		String needWriteMessage = myLogSdf.format(nowtime) + "    " + lever
				+ "    " + tag + "    " + text;
		File file = new File(MYLOG_PATH_SDCARD_DIR, MYLOGFILEName);
		FileWriter filerWriter;
		try {
			if (file.length() >= 1024*1024) {
				filerWriter = new FileWriter(file, false);// 后面这个参数代表是不是要接上文件中原来的数据，不进行覆盖
			} else {
				filerWriter = new FileWriter(file, true);// 后面这个参数代表是不是要接上文件中原来的数据，不进行覆盖
			}
			BufferedWriter bufWriter = new BufferedWriter(filerWriter);
			bufWriter.write(needWriteMessage);
			bufWriter.write("\r\n");
			bufWriter.newLine();
			bufWriter.close();
			filerWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 删除制定的日志文件
	 * */
	public static void delFile() {// 删除日志文件
		String needDelFiel = logfile.format(getDateBefore());
		File file = new File(MYLOG_PATH_SDCARD_DIR, needDelFiel + MYLOGFILEName);
		if (file.exists()) {
			file.delete();
		}
	}

	/**
	 * 得到现在时间前的几天日期，用来得到需要删除的日志文件名
	 * */
	private static Date getDateBefore() {
		Date nowtime = new Date();
		Calendar now = Calendar.getInstance();
		now.setTime(nowtime);
		now.set(Calendar.DATE, now.get(Calendar.DATE)
				- SDCARD_LOG_FILE_SAVE_DAYS);
		return now.getTime();
	}
}
