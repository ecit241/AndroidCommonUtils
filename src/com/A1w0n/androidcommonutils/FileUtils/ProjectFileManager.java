package com.A1w0n.androidcommonutils.FileUtils;

import java.io.File;

import android.text.TextUtils;

import com.A1w0n.androidcommonutils.GlobalApplicationUtils.GlobalApplication;
import com.A1w0n.androidcommonutils.debugutils.Logger;

/**
 * @author A1w0n
 * 项目必须确保，项目用到的所有File对象，都是这里出来的！
 * 
 * 会自动为每个需要创建的文件夹创建.nomedia文件
 */
public class ProjectFileManager {
	
	public static final String PATH_A1W0N = "A1w0n";
	public static final String PATH_APK_ICON = PATH_A1W0N + File.separator + "apkIcons";
	public static final String FILENAME_ALL_APPS_INFO = "app.info";
	public static final String PATH_ALL_APPS_INFO_FILE = PATH_A1W0N + File.separator + FILENAME_ALL_APPS_INFO;
	

	public ProjectFileManager() {
	}
	
	public static File getIconFileForWriting(String iconFileName) {
		String relativePath = PATH_APK_ICON + File.separator + iconFileName + ".png";
		return getFile(relativePath);
	}
	
	public static File getAllAppsInfoFileForWriting() {
		String relativePath = PATH_ALL_APPS_INFO_FILE;
		return getFile(relativePath);
	}
	
	private static File getFile(String relativePath) {
		File result = null;
		
		if (TextUtils.isEmpty(relativePath)) {
			Logger.e("Try to access a file with empty relative path!");
			return result;
		}
		
		if (AndroidFileUtils.isExternalStorageWritable()) {
			result = AndroidFileUtils.getOrCreateFileOnExternalStorageRelative(relativePath);
		} else {
			result = AndroidFileUtils.getOrCreateFileOnInternalStorage(GlobalApplication.getInstance(), relativePath);
		}
		
		return result;
	}
	
	/**
	 * 整个项目，只知道相对路径，而不用关系内部存储还是外部存储，要想得到文件夹的话，
	 * 调用这个方法，通过相对路径获取文件夹
	 * @param relativePath
	 * @return
	 */
	private static File getDirectory(String relativePath) {
		File result = null;
		
		if (TextUtils.isEmpty(relativePath)) {
			Logger.e("Try to access a directory with empty relative path!");
			return result;
		}
		
		if (AndroidFileUtils.isExternalStorageWritable()) {
			result = AndroidFileUtils.getOrCreateDirectoryOnExternalStorage(relativePath);
		} else {
			result = AndroidFileUtils.getOrCreateDirectoryOnInternalStorage(GlobalApplication.getInstance(), relativePath);
		}
		
		return result;
	}
	
	
	
	

}
