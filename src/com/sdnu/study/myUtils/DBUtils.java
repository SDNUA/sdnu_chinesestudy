package com.sdnu.study.myUtils;

import com.sdnu.study.db.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBUtils {
	
	// 默认数据库  
    private static final String DB_NAME = "asc.db";  
    // 数据库版本  
    private static final int DB_VERSION = 1;  
  
    // 执行open()打开数据库时，保存返回的数据库对象  
    private SQLiteDatabase mSQLiteDatabase = null;  
  
    // 由SQLiteOpenHelper继承过来  
    private DBHelper mDatabaseHelper=null;  
  
    // 本地Context对象  
    private Context mContext = null;  
      
    private static DBUtils dbConn= null;  
      
    // 查询游标对象  
    private Cursor cursor;  
    //SQLiteOpenHelper
   
    /** 
     * 构造函数 
     *  
     * @param mContext 
     */  
    private DBUtils(Context mContext) {  
        super();  
        this.mContext = mContext;  
    }  
      
    public static DBUtils getInstance(Context mContext){  
        if (null == dbConn) {  
            dbConn = new DBUtils(mContext);  
        }  
        return dbConn;  
    }  
  
    /** 
     * 打开数据库 
     */  
    public void open() {  
        mDatabaseHelper = new DBHelper(mContext);  
        mSQLiteDatabase = mDatabaseHelper.getWritableDatabase();  
    }  
  
    /** 
     * 关闭数据库 
     */  
    public void close() {  
        if (null != mDatabaseHelper) {  
            mDatabaseHelper.close();  
        }  
        if (null != cursor) {  
            cursor.close();  
        }  
    }  
  
    /** 
     * 插入数据 
     * @param tableName 表名 
     * @param nullColumn null 
     * @param contentValues 名值对 
     * @return 新插入数据的ID，错误返回-1 
     * @throws Exception 
     */  
    public long insert(String tableName, String nullColumn,  
            ContentValues contentValues) throws Exception {  
        try {  
            return mSQLiteDatabase.insert(tableName, nullColumn, contentValues);  
        } catch (Exception e) {  
            throw e;  
        }  
    }  
  
    /** 
     * 通过主键ID删除数据 
     * @param tableName 表名 
     * @param key 主键名 
     * @param id 主键值 
     * @return 受影响的记录数 
     * @throws Exception 
     */  
    public long delete(String tableName, String key, int id) throws Exception {  
        try {  
            return mSQLiteDatabase.delete(tableName, key + " = " + id, null);  
        } catch (Exception e) {  
            throw e;  
        }  
    }  
      
    /** 
     * 查找表的所有数据 
     * @param tableName 表名 
     * @param columns 如果返回所有列，则填null 
     * @return 
     * @throws Exception 
     */  
    public Cursor findAll(String tableName, String [] columns) throws Exception{  
        try {  
            cursor = mSQLiteDatabase.query(tableName, columns, null, null, null, null, null);  
            cursor.moveToFirst();  
            return cursor;  
        } catch (Exception e) {  
            throw e;  
        }  
    }  
      
    /** 
     * 根据主键查找数据 
     * @param tableName 表名 
     * @param key 主键名 
     * @param id  主键值 
     * @param columns 如果返回所有列，则填null 
     * @return Cursor游标 
     * @throws Exception  
     */  
    public Cursor findById(String tableName, String key, int id, String [] columns) throws Exception {  
        try {  
            return mSQLiteDatabase.query(tableName, columns, key + " = " + id, null, null, null, null);  
        } catch (Exception e) {  
            throw e;  
        }  
    }  
      
    /** 
     * 根据条件查询数据 
     * @param tableName 表名 
     * @param names 查询条件 
     * @param values 查询条件值 
     * @param columns 如果返回所有列，则填null 
     * @param orderColumn 排序的列 
     * @param limit 限制返回数 
     * @return Cursor游标 
     * @throws Exception 
     */  
    public Cursor find(String tableName, String [] names, String [] values, String [] columns, String orderColumn, String limit) throws Exception{  
        try {  
            StringBuffer selection = new StringBuffer();  
            for (int i = 0; i < names.length; i++) {  
                selection.append(names[i]);  
                selection.append(" = ?");  
                if (i != names.length - 1) {  
                    selection.append(",");  
                }  
            }  
            cursor = mSQLiteDatabase.query(true, tableName, columns, selection.toString(), values, null, null, orderColumn, limit);  
            cursor.moveToFirst();  
            return cursor;  
        } catch (Exception e) {  
            throw e;  
        }  
    }  
      
    /** 
     *  
     * @param tableName 表名 
     * @param names 查询条件 
     * @param values 查询条件值 
     * @param args 更新列-值对 
     * @return true或false 
     * @throws Exception 
     */  
    public boolean udpate(String tableName, String [] names, String [] values, ContentValues args) throws Exception{  
        try {  
            StringBuffer selection = new StringBuffer();  
            for (int i = 0; i < names.length; i++) {  
                selection.append(names[i]);  
                selection.append(" = ?");  
                if (i != names.length - 1) {  
                    selection.append(",");  
                }  
            }  
            return mSQLiteDatabase.update(tableName, args, selection.toString(), values) > 0;  
        } catch (Exception e) {  
            throw e;  
        }  
    }  
      
    /** 
     * 执行sql语句，包括创建表、删除、插入 
     *  
     * @param sql 
     */  
    public void executeSql(String sql) {  
        mSQLiteDatabase.execSQL(sql);  
    }  
  
} 
