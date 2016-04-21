package com.sdnu.study.test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import com.sdnu.study.config.MyConfig;
import com.sdnu.study.domain.PinyinTableItem;
import com.sdnu.study.domain.Version;
import com.sdnu.study.myUtils.DBUtils;
import com.sdnu.study.myUtils.PullXMLUtils;

import android.content.res.AssetManager;
import android.database.Cursor;
import android.test.AndroidTestCase;

public class DBTest extends AndroidTestCase {
	
	
	public void testAdd() throws Exception{
		
		DBUtils utils=DBUtils.getInstance(getContext());
		utils.open();
		//String arrStr[]={"b","波","bo","xxx","xxx"};
		String arrStr[]={"c","波","bo","xxx","xxx"};
		utils.insert("pinyin", arrStr);
		utils.close();
		
	}
	public void testFind() throws Exception{
		AssetManager am=getContext().getAssets();
		InputStream in=am.open("version.xml");
		Version version = PullXMLUtils.parseVersion(in);
		System.out.println(version);
		
	}
	
	
	public void TestExerciseData() throws Exception{
		/*DBUtils dbUtil=DBUtils.getInstance(getContext());
		dbUtil.openMyDb(MyConfig.DB_NAME);
		Cursor cursor= dbUtil.getExerciseData(1);
		 while (cursor.moveToNext()) {
			 System.out.println(cursor.getString(cursor.getColumnIndex("word_han")));
		 }
		 
		 
		dbUtil.close(MyConfig.DB_NAME);*/
	}
}
