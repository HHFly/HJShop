package com.mark.app.hjshop4a.common.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.mark.app.hjshop4a.common.sqlite.helper.SQLiteContextUtils;
import com.mark.app.hjshop4a.common.sqlite.helper.SQLiteUtils;


/**
 * Created by pc on 2018/4/9.
 */

public class CountryUtils {
    private static CountryUtils utils;
    private final String TABLE_NAME = SQLiteContextUtils.TABLE.COUNTRY.NAME;
    private SQLiteUtils dbUtils;
    public CountryUtils(Context context) {
        dbUtils = SQLiteUtils.getInstance(context);
    }

    /**
     * 获取实例(单例模式)
     *
     * @param context
     * @return
     */
    public static CountryUtils getInstance(Context context) {
        if (utils == null) {
            synchronized (CountryUtils.class) {
                if (utils == null) {
                    utils = new CountryUtils(context);
                }
            }
        }
        return utils;
    }
    /**
     * 获取已经存在的记录
     *
     * @return
     */
    public String select() {

        String result ="";
        Cursor cursor  =dbUtils.query(TABLE_NAME,null,null);
        if(cursor !=null) {
            while (cursor.moveToNext())
            result = cursor.getString(cursor.getColumnIndex("country"));
        }
        return result;
    }
    /**
     * 插入
     *
     * @param
     * @param
     * @param
     * @return
     */
    public boolean insert(String country) {

        ContentValues values = new ContentValues();
        values.put(SQLiteContextUtils.TABLE.COUNTRY.FIELD.COUNTRY,country);
        long count = dbUtils.insert(TABLE_NAME, values);
        return count > 0;
    }
  /*
  * 删除
  * */
  public boolean delete(){
      long count = dbUtils.delete(TABLE_NAME,null,null);
      return count > 0;
  }
}
