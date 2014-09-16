package com.lee.service;

import java.io.File;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
/**
 * 
 * @author William Lee
 *
 */
public class MemoryService {
	/**
	 * ���SD���ܴ�С
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getSDTotalSize(Context context) {
		File path = Environment.getExternalStorageDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long totalBlocks = stat.getBlockCount();
		return Formatter.formatFileSize(context, blockSize * totalBlocks);
	}

	/**
	 * ���sd��ʣ�������������ô�С
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getSDAvailableSize(Context context) {
		File path = Environment.getExternalStorageDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getAvailableBlocks();
		return Formatter.formatFileSize(context, blockSize * availableBlocks);
	}

	/**
	 * ��û����ڴ��ܴ�С
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getRomTotalSize(Context context) {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long totalBlocks = stat.getBlockCount();
		return Formatter.formatFileSize(context, blockSize * totalBlocks);
	}

	/**
	 * ��û�������ڴ�
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getRomAvailableSize(Context context) {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getAvailableBlocks();
		return Formatter.formatFileSize(context, blockSize * availableBlocks);
	}

}
