package com.sdnu.study.myUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sdnu.study.db.DBHelper;
import com.sdnu.study.domain.ExerciseAnswerItem;
import com.sdnu.study.domain.NewWord;
import com.sdnu.study.domain.ShortDialogItem;

public class DBUtils {
	
	// 默认数据库  
    private static final String DB_NAME = "test.db";  
    // 数据库版本  
    private static final int DB_VERSION = 1;  
  
    // 执行open()打开数据库时，保存返回的数据库对象  
    private SQLiteDatabase mSQLiteDatabase = null;  
    AssetsDatabaseManager mg=null;
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
    public void openMyDb(String dbName) {  
    	AssetsDatabaseManager.initManager(mContext);
		mg = AssetsDatabaseManager.getManager();
		mSQLiteDatabase=mg.getDatabase(dbName);  
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
    public void close(String dbfile) {  
    	if(mg!=null){
    		mg.closeDatabase(dbfile);
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
      

   
    public List<ShortDialogItem> getDialogData(int key) {
		
		List<ShortDialogItem> list=new ArrayList<ShortDialogItem>();
		ShortDialogItem sdi=null;
		Cursor cursor = mSQLiteDatabase.rawQuery("select * from tb_dialog where dialog_num="+key, null);
		while (cursor.moveToNext()) {
			sdi=new ShortDialogItem();
			sdi.setChinese(cursor.getString(cursor.getColumnIndex("dialog_chinese")));
			sdi.setPinyin(cursor.getString(cursor.getColumnIndex("dialog_pinyin")));
			list.add(sdi);
		}
		if(cursor!=	null){
			cursor.close();
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
    
    public List<Map<String, String>> getCourseTitle() {
		
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		Cursor cursor = mSQLiteDatabase.rawQuery("select * from tb_title", null);
		while (cursor.moveToNext()) {
			map = new HashMap<String, String>();
			StringBuffer tiltle = new StringBuffer();
			tiltle.append("第"
					+ cursor.getString(cursor.getColumnIndex("class_no"))
					+ "课\t");
			tiltle.append(cursor.getString(cursor
					.getColumnIndex("class_chinese_title")));
			map.put("title", tiltle.toString());
			data.add(map);
		}
		if(cursor!=	null){
			cursor.close();
		}
		return data;
	}

	public List<NewWord> getWordsData(int key) {
		List<NewWord> list=new ArrayList<NewWord>();
		NewWord nw=null;
		Cursor cursor = mSQLiteDatabase.rawQuery("select * from tb_words where class_no="+key, null);
		while (cursor.moveToNext()) {
			nw=new NewWord();
			nw.setHanChar(cursor.getString(cursor.getColumnIndex("word_chinese")));
			nw.setEnglishChar(cursor.getString(cursor.getColumnIndex("word_english")));
			nw.setPinyinChar(cursor.getString(cursor.getColumnIndex("word_pinyin")));
			list.add(nw);
		}
		if(cursor!=	null){
			cursor.close();
		}
		return list;
	}
	
	public ArrayList<ExerciseAnswerItem> getExerciseData(int key) {
		ArrayList<ExerciseAnswerItem> list=new ArrayList<ExerciseAnswerItem>();
		ExerciseAnswerItem eai=null;
		
		Cursor cursor = mSQLiteDatabase.rawQuery("select a.word_chinese,a.word_pinyin,a.word_english,b.answer_a,b.answer_b from tb_words as a,tb_fyexercice as b where a.id=b.fk_words and course_num="+key, null);
		while (cursor.moveToNext()) {
			eai=new ExerciseAnswerItem();
			eai.setAnswer(cursor.getString(cursor.getColumnIndex("answer_a")));
			eai.setAnswerFlag(eai.getAnswerA());
			list.add(eai);
			eai=new ExerciseAnswerItem();
			eai.setAnswer(cursor.getString(cursor.getColumnIndex("answer_b")));
			eai.setAnswerFlag(eai.getAnswerB());
			list.add(eai);
			eai=new ExerciseAnswerItem();
			eai.setAnswer(cursor.getString(cursor.getColumnIndex("word_py")));
			eai.setAnswerFlag(eai.getAnswerC());
			list.add(eai);
		}
		if(cursor!=	null){
			cursor.close();
		}
		return list;
	}

	public List<Map<String, String>> getBihuaList() {
		List<Map<String, String>> list=new ArrayList<Map<String,String>>();
		Map<String, String> map=null;
		Cursor cursor = mSQLiteDatabase.rawQuery("select * from tb_bihua", null);
		while (cursor.moveToNext()) {
			map=new HashMap<String, String>();
			map.put("bihua",cursor.getString(cursor.getColumnIndex("bihua")));
			map.put("bihua_py",cursor.getString(cursor.getColumnIndex("bihua_py")));
			list.add(map);
		}
		return list;
	}
    
    
  
} 
