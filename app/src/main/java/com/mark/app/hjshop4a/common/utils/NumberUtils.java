package com.mark.app.hjshop4a.common.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 功能：数值加工厂
 * 作者：林敬聚
 */

public class NumberUtils {
    //格式化日期
    private static final String PARAM_DATETIME = "yyyy-MM-dd HH:mm:ss";
    //格式化日期
    private static final String PARAM_DATETIME_YMDHM = "yyyy-MM-dd HH:mm";
    //格式化日期
    private static final String PARAM_DATETIME_MD_HMS = "MM-dd HH:mm:ss";
    //格式化日期
    private static final String PARAM_DATETIME_HMS = "HH:mm:ss";
    //格式化日期
    private static final String PARAM_DATETIME_HM = "HH:mm";
    //格式化日期
    private static final String PARAM_DATETIME_MD_HM = "MM-dd HH:mm";
    //格式化日期
    private static final String PARAM_DATETIME_YMD = "yyyy-MM-dd";
    //格式化日期
    private static final String PARAM_DATETIME_MS = "（剩余 mm 分 ss 秒）";

    //格式化数值
    private static final String PARAM_KEEP_DECIMAL_TWO = "#0.00";
    //格式化数值
    private static final String PARAM_KEEP_DECIMAL_ONE = "#0.0";
    //格式化数值
    private static final String PARAM_KEEP_DECIMAL = "#0";
    //格式化数值
    private static final String PARAM_KEEP_DECIMAL00 = "#00";

    public static double parseDouble(String data) {
        double d = 0;
        try {
            d = Double.parseDouble(data);
        } catch (Exception e) {
            LogUtils.logFormat("NumberUtils", "parseDouble", "d = Double.parseDouble(data);");
        }
        return d;
    }

    /**
     * 将毫秒转换为从0开始的时间
     * 例如13:00
     *
     * @param data
     * @return
     */
    public static String getTimeHM(long data) {
        //所有秒
        int allSecond = (int) (data / 1000l);
        //所有分
        int allMinute = allSecond / 60;
        //所有小时
        int allHour = allMinute / 60;
        //小时
        int hour = allHour % 24;
        if (hour == 0 && allHour != 0) {
            hour = 24;
        }

        //分
        int minute = allMinute % 60;

        return keepDecimal00(hour) + ":" + keepDecimal00(minute);
    }

    /**
     * 格式化数值
     * 00
     *
     * @param number number
     * @return String
     */
    private static String keepDecimal00(double number) {
        return keepDecimal(number, PARAM_KEEP_DECIMAL00);
    }


    /**
     * 格式化数值
     * 0.0
     *
     * @param number number
     * @return String
     */
    public static String keepDecimalOne(double number) {
        return keepDecimal(number, PARAM_KEEP_DECIMAL_ONE);
    }

    /**
     * 格式化数值
     * #0
     *
     * @param number number
     * @return String
     */
    public static String keepDecimalZero(double number) {
        return keepDecimal(number, PARAM_KEEP_DECIMAL);
    }

    /**
     * 格式化数值
     * 0.00
     *
     * @param number number
     * @return String
     */
    public static String keepDecimal(String number) {
        try {
            double d = Double.valueOf(number);
            return keepDecimal(d, PARAM_KEEP_DECIMAL_TWO);
        } catch (Exception e) {
            LogUtils.logFormat("NumberUtils", "keepDecimal", "字符串转换double异常");
        }
        return number;
    }

    /**
     * 格式化数值
     * 0.00
     *
     * @param number number
     * @return String
     */
    public static String keepDecimal(double number) {
        return keepDecimal(number, PARAM_KEEP_DECIMAL_TWO);
    }

    /**
     * 格式化日期(1-1 1:00)
     * yyyy-MM-dd HH:mm:ss
     *
     * @param dateTime
     * @return
     */
    public static String getFormatDateTimeMDHMS(long dateTime) {
        return getFormatDateTime(PARAM_DATETIME_MD_HMS, dateTime);
    }

    /**
     * 格式化日期（剩余 mm 分 ss 秒）
     * yyyy-MM-dd HH:mm:ss
     *
     * @param dateTime
     * @return
     */
    public static String getFormatDateTimeMS(long dateTime) {
        return getFormatDateTime(PARAM_DATETIME_MS, dateTime);
    }

    /**
     * 格式化日期
     * HH:mm:ss
     *
     * @param dateTime
     * @return
     */
    public static String getFormatDateTimeHMS(long dateTime) {
        return getFormatDateTime(PARAM_DATETIME_HMS, dateTime);
    }

    /**
     * 格式化日期
     * HH:mm
     *
     * @param dateTime
     * @return
     */
    public static String getFormatDateTimeHM(long dateTime) {
        return getFormatDateTime(PARAM_DATETIME_HM, dateTime);
    }

    /**
     * 格式化日期(2000-1-1)
     * yyyy-MM-dd HH:mm:ss
     *
     * @param dateTime
     * @return
     */
    public static String getFormatDateTimeMDHM(long dateTime) {
        return getFormatDateTime(PARAM_DATETIME_MD_HM, dateTime);
    }

    /**
     * 格式化日期(2000-1-1)
     * yyyy-MM-dd HH:mm:ss
     *
     * @param dateTime
     * @return
     */
    public static String getFormatDateTimeYMD(long dateTime) {
        return getFormatDateTime(PARAM_DATETIME_YMD, dateTime);
    }

    /**
     * 格式化日期
     * yyyy-MM-dd HH:mm:ss
     *
     * @param dateTime
     * @return
     */
    public static String getFormatDateTimeYMDHMS(long dateTime) {
        return getFormatDateTime(PARAM_DATETIME, dateTime);
    }

    /**
     * 格式化日期
     * yyyy-MM-dd HH:mm:ss
     *
     * @param dateTime
     * @return
     */
    public static String getFormatDateTimeYMDHM(long dateTime) {
        return getFormatDateTime(PARAM_DATETIME_YMDHM, dateTime);
    }

    /**
     * 格式化数值
     *
     * @param number number
     * @return String
     */
    private static String keepDecimal(double number, String pattern) {
        DecimalFormat format = new DecimalFormat(pattern);
        String result = format.format(number);
        return result;
    }

    /**
     * 格式化日期
     *
     * @param pattern
     * @param dateTime
     * @return
     */
    private static String getFormatDateTime(String pattern, long dateTime) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        format.setTimeZone(TimeZone.getTimeZone("GMT-0:00"));
        return format.format(new Date(dateTime));
    }
    /*
  * 判断获取 long 型时间 or String 型时间
  * */
    public static String getTimeStrYMDHMS(long timelong , String timeStr ){
        String strTime ="";
        if(timeStr==null){
        //    strTime = NumberUtils.getFormatDateTimeYMDHMS(timelong);
            return  strTime;
        }else {
            if(timeStr.isEmpty()){
   //             strTime = NumberUtils.getFormatDateTimeYMDHMS(timelong);]
              strTime=  timeStr;
            }else {
                strTime =timeStr;
            }
        }
        return strTime;
    }
    /*
    * 判断获取 long 型时间 or String 型时间
    * */
    public static String getTimeStrYMDHM(long timelong , String timeStr ){
        String strTime ="";
        if(timeStr==null){
     //       strTime = NumberUtils.getFormatDateTimeYMDHM(timelong);
            return strTime;
        }else {
            if(timeStr.isEmpty()){
          //      strTime = NumberUtils.getFormatDateTimeYMDHM(timelong);
                strTime=timeStr;
            }else {
                strTime =timeStr;
            }
        }
        return strTime;
    }
    /*
   * 判断获取 long 型时间 or String 型时间
   * */
    public static String getTimeStrYMD(long timelong , String timeStr ){
        String strTime ="";
        if(timeStr==null){
           // strTime = NumberUtils.getFormatDateTimeYMD(timelong);
            return strTime;
        }else {
            if(timeStr.isEmpty()){
           //     strTime = NumberUtils.getFormatDateTimeYMD(timelong);
                strTime =timeStr;
            }else {
                strTime =timeStr;
            }
        }
        return strTime;
    }
    /*
   * 判断获取 long 型时间 or String 型时间
   * */
    public static String getTimeStrMS(long timelong , String timeStr ){
        String strTime ="";
        if(timeStr==null){
            //strTime = NumberUtils.getFormatDateTimeMS(timelong);
            return  strTime;
        }else {
            if(timeStr.isEmpty()){
         //       strTime = NumberUtils.getFormatDateTimeMS(timelong);
               strTime= timeStr;
            }else {
                strTime =timeStr;
            }
        }
        return strTime;
    }
    /*
  * 判断获取 long 型时间 or String 型时间 (不作处理)
  * */
    public static String getTimeStrHM(long timelong , String timeStr ){
        String strTime ="";
        if(timeStr==null){
            //strTime = NumberUtils.getFormatDateTimeHM(timelong);
            return strTime;
        }else {
            if(timeStr.isEmpty()){
           //     strTime = NumberUtils.getFormatDateTimeHM(timelong);
                strTime =timeStr;
            }else {
                strTime =timeStr;
            }
        }
        return strTime;
    }
}
