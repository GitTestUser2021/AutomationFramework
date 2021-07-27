package com.framework.utils;

import java.io.File;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;

/*
 * Class to handle log file
 * 
 * @author 10675365
 * 
 */

public class LoggerHelper {
	private static boolean root = false;
	public static String logPath;
	private static String logFilePath;

	public synchronized static void createLogFolder() {
		String userDir = System.getProperty("user.dir");
		String folderName = CalendarUtils.getTimeStamp("ddMMyyyy_HH mm ss");
		logPath = userDir + "/logs/" + folderName;
		logFilePath=logPath+"logs.log";
		new File(LoggerHelper.logPath).mkdir();
	}

	public synchronized static Logger getLogger(Class<?> cls) {
		if (root) {
			return Logger.getLogger(cls);
		}
		LoggerHelper.createLogFolder();
		PropertiesConfiguration config;
		try {
			config = new PropertiesConfiguration("log4j.properties");
			config.setProperty("logFolder", LoggerHelper.logPath);
			config.save();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		PropertyConfigurator.configure("log4j.properties");
		root = true;
		return Logger.getLogger(cls);
	}
	
	public static void shutdown() {
		org.apache.log4j.LogManager.shutdown();
	}
	
	public static String getGetLogFilePath() {
		return logFilePath;
	}
}
