package com.mark.app.hjshop4a.common.sqlite.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * SQLiteOpenHelper
 * Created by lenovo on 2017/10/4.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context context) {
        super(context, SQLiteContextUtils.DB_NAME, null, SQLiteContextUtils.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建购物车表
        db.execSQL(SQLiteContextUtils.TABLE.SHOPCAR.SQL.CREATE);
        //创建公共数据表
        db.execSQL(SQLiteContextUtils.TABLE.COMMON.SQL.CREATE);
        //创建国家数据表
        db.execSQL(SQLiteContextUtils.TABLE.COUNTRY.SQL.CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //删除旧的购物车表
        db.execSQL(SQLiteContextUtils.TABLE.SHOPCAR.SQL.DELETE);

        //创建购物车表
        db.execSQL(SQLiteContextUtils.TABLE.SHOPCAR.SQL.CREATE);
        //删除旧国家数据表
        db.execSQL(SQLiteContextUtils.TABLE.COUNTRY.SQL.DELETE);
        //创建国家数据表
        db.execSQL(SQLiteContextUtils.TABLE.COUNTRY.SQL.CREATE);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);

        //删除旧的购物车表
        db.execSQL(SQLiteContextUtils.TABLE.SHOPCAR.SQL.DELETE);

        //创建购物车表
        db.execSQL(SQLiteContextUtils.TABLE.SHOPCAR.SQL.CREATE);
        //删除旧国家数据表
        db.execSQL(SQLiteContextUtils.TABLE.COUNTRY.SQL.DELETE);
        //创建国家数据表
        db.execSQL(SQLiteContextUtils.TABLE.COUNTRY.SQL.CREATE);
    }
}
