package com.sdnu.study.myUtils;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sdnu.study.db.DBHelper;
import com.sdnu.study.domain.PinyinTableItem;

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
     *  
     * @param tableName 输入数组
     * @param arrStr 表明
     */
    public void insert(String tableName,String[] arrStr){
    	
    	String sql="insert into pinyin(mchar,hanzi,pinyin,pic_path,duyin) values(?,?,?,?,?);";
    	mSQLiteDatabase.execSQL(sql, arrStr);
    }  
  
    /** 
     * 通过主键ID删除数据 
     * @param tableName 表名 
     * @param key 主键名 
     * @param id 主键值 
     * @return 受影响的记录数 
     * @throws Exception 
     */  
    public void delete(String tableName, String key, int id) {  
        
        	String sql="delete from "+tableName+" where id="+id;
            mSQLiteDatabase.execSQL(sql);;  
        
    }
    
    /**
     * 
     * @param tableName
     */
    
    public List<PinyinTableItem> findPinYinTableItem() {  
        
    	List<PinyinTableItem> list=new ArrayList<PinyinTableItem>();
    	String sql="select * from pinyin";
    	cursor=mSQLiteDatabase.rawQuery(sql,null);
    	while (cursor.moveToNext()) {
    		PinyinTableItem item=new PinyinTableItem();
//    		item.setmChar(cursor.getString(cursor.getColumnIndex("mchar")));
//    		item.setHanzi(cursor.getString(cursor.getColumnIndex("hanzi")));
//    		item.setPinyin(cursor.getString(cursor.getColumnIndex("pinyin")));
    		list.add(item);
		}
    return list;
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
