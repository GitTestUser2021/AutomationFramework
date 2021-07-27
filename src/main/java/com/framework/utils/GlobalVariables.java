package com.framework.utils;

import java.util.Set;

import com.aventstack.extentreports.ExtentReports;

public class GlobalVariables {
public static PropertyFileUtils configProp=null;
public static Set<String> featureList;
public static String currentFeature;


public static String browserName;
public static String driverName;
public static int waitTime=20;
public static String applicationName;

public static String applicationUrl=null;

//public static ExtentReportManager extentReportManagerObj;
public static ExtentReports extentReport;
}
