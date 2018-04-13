package com.mark.app.hjshop4a.common.sqlite.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mark.app.hjshop4a.app.AppContext;
import com.mark.app.hjshop4a.common.utils.LogUtils;


/**
 * 数据库操作工具类
 * Created by lenovo on 2017/10/4.
 */

public class SQLiteUtils {
    private Context mContext;
    //数据库帮助类
    private SQLiteDatabase mDb;

    public SQLiteUtils(Context context) {
        mContext = context.getApplicationContext();
    }

    /**
     * 获取实例（非单例模式）
     *
     * @param context
     * @return
     */
    public static SQLiteUtils getInstance(Context context) {
        return new SQLiteUtils(context);
    }

    /**
     * 获取数据库
     *
     * @return
     */
    public SQLiteDatabase getDB() {
        if (mDb == null || !mDb.isOpen()) {
            mDb = new SQLiteHelper(mContext).getWritableDatabase();
        }
        return mDb;
    }

    /**
     * 开启事务
     */
    public void beginTransaction() {
        SQLiteDatabase db = getDB();
        if (db != null) {
            try {
                db.beginTransaction();
            } catch (Exception e) {
                LogUtils.logFormat(this, "endTransaction", "异常");
                if (AppContext.isDebudEnv()) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 事务完成
     */
    public void setTransactionSuccessful() {
        SQLiteDatabase db = getDB();
        if (db != null) {
            try {
                db.setTransactionSuccessful();
            } catch (Exception e) {
                LogUtils.logFormat(this, "endTransaction", "异常");
                if (AppContext.isDebudEnv()) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 保存事务
     */
    public void endTransaction() {
        SQLiteDatabase db = getDB();
        if (db != null) {
            try {
                db.endTransaction();
            } catch (Exception e) {
                LogUtils.logFormat(this, "endTransaction", "异常");
                if (AppContext.isDebudEnv()) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 关闭数据库
     */
    public void close() {
        mDb.close();
    }


    /**
     * 插入
     *
     * @return
     */
    public long insert(String table, ContentValues values) {
        try {
            SQLiteDatabase db = getDB();
            return db.insert(table, null, values);
        } catch (Exception e) {
            LogUtils.logFormat(this, "insert", "数据库操作异常");
            if (AppContext.isDebudEnv()) {
                e.printStackTrace();
            }
            return 0;
        }
    }

    /**
     * 更新语句
     * ContentValues cv = new ContentValues();
     * cv.put("username", "c");
     * cv.put("password", "d");
     * String[] args = {String.valueOf("a")};
     * update("user", cv, "username=?",args)
     *
     * @param table
     * @param values
     * @param where
     * @return
     */
    public int update(String table, ContentValues values, String where, String[] whereArgs) {
        try {
            SQLiteDatabase db = getDB();
            return db.update(table, values, where, whereArgs);
        } catch (Exception e) {
            LogUtils.logFormat(this, "insert", "数据库操作异常");
            if (AppContext.isDebudEnv()) {
                e.printStackTrace();
            }
            return 0;
        }
    }

    /**
     * 删除语句
     *
     * @param table
     * @param where
     * @param whereArgs
     * @return
     */
    public int delete(String table, String where, String[] whereArgs) {
        try {
            SQLiteDatabase db = getDB();
            return db.delete(table, where, whereArgs);
        } catch (Exception e) {
            LogUtils.logFormat(this, "insert", "数据库操作异常");
            if (AppContext.isDebudEnv()) {
                e.printStackTrace();
            }
            return 0;
        }
    }

    /**
     * 查询语句
     *
     * @param table
     * @param where
     * @param whereArgs
     * @return
     */
    public Cursor query(String table, String where, String[] whereArgs) {
        try {
            SQLiteDatabase db = getDB();
            return db.query(table, null, where, whereArgs, null, null, null);
        } catch (Exception e) {
            LogUtils.logFormat(this, "insert", "数据库操作异常");
            if (AppContext.isDebudEnv()) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
