package com.xin.easyextension.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
* @author Xin Chen
* @email cxscy@126.com
* @date 2018-05-25
* @version 1.0
* @Description
*/
public class CommonUtil {

	public static String getID() {
		Date date = new Date();
		String time=new SimpleDateFormat("yyyyMMddHHmmssSSS").format(date);
		Random rand = new Random();
		int randomInt=rand.nextInt(100) + 1;
		String randomStr=String.format("%03d", randomInt);
		return time+randomStr;
	}
	
	public static boolean deleteFile(File dirFile) {
		// 如果dir对应的文件不存在，则退出
		if (!dirFile.exists()) {
			return false;
		}

		if (dirFile.isFile()) {
			return dirFile.delete();
		} else {

			for (File file : dirFile.listFiles()) {
				deleteFile(file);
			}
		}

		return dirFile.delete();
	}
}
