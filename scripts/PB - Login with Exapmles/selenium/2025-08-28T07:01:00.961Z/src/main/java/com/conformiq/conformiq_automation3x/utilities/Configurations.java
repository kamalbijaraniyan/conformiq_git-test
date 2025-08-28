package com.conformiq.conformiq_automation3x.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Configurations {
    static DateFormat dateFormat = new SimpleDateFormat("yyyy//MM//dd HH:mm:ss");
    static Date date = new Date();
    static String timestamp = dateFormat.format(date);
    static String newChar = getNewString(timestamp);
    public static final String projectName = "conformiq-automation3x";
    public static String browserType = "Chrome";
    public static String screenshotLocation = "HTMLReports/" + newChar + "/";
    public static String htmlReportLocation = "HTMLReports/" + newChar + "/";
    public static String url = "";
    public static String hosturl = "http://127.0.0.1:4723/wd/hub";
    public static String deviceName = "MyPhone";
    public static String Version = "6.0.0";
    public static String DRIVER_PATH = System.getProperty("user.dir") + "//src//test//resources//{pathToDriver}";

    public static String getNewString(String newStr) {
        String newString = newStr;
        String charectersToAvoid = "[;,`{}\"~=\\/:*?\"<>'|-+&^%$#@!]. ";
        for (int cnt = 0; cnt < charectersToAvoid.length(); cnt++) {
            newString = newString.replace(charectersToAvoid.charAt(cnt), '_');
        }
        return newString;
    }

}