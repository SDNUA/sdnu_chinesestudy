package com.sdnu.study.test;

import java.util.Iterator;
import java.util.List;

import com.sdnu.study.domain.PinyinTableItem;
import com.sdnu.study.myUtils.DBUtils;

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
		
		/*DBUtils utils=DBUtils.getInstance(getContext());
		utils.open();
		
		List<PinyinTableItem> list=utils.findPinYinTableItem();
		for (PinyinTableItem item:list) {
			System.out.println("-------------");
			//System.out.println(item.getHanzi());
			System.out.println("-------------");
		}
		
		utils.close();*/
		
	}

}
