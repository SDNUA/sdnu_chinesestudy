package com.sdnu.study.db;

import com.sdnu.study.config.MyConfig;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	
	 //数据库名称
     //数据库版本
	public DBHelper(Context context) {
		super(context, MyConfig.DB_NAME, null, MyConfig.DB_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table pinyin(id integer primary key autoincrement,mchar varchar(4),hanzi varchar(4),pinyin varchar(10),pic_path varchar(32),duyin varchar(32));"); 
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS ad_record");
		//onCreate(db);
	}

}
