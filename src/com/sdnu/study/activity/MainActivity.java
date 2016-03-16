package com.sdnu.study.activity;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sdnu.study.domain.PinyinTableItem;
import com.sdnu.study.myUtils.DBUtils;
import com.sdnu.study.myview.MySlidingMenu;


public class MainActivity extends Activity {
	private MySlidingMenu slidingMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //侧滑菜单
    	slidingMenu=new MySlidingMenu(this);
    	slidingMenu.showSlidingMenu();
    	
    	
    }
    
    private void setData() {
		// TODO Auto-generated method stub
    	DBUtils utils=DBUtils.getInstance(this);
		utils.open();
	
		//utils.insert("pinyin", arrStr);
		utils.close();
	}
    
   public void btnClick(View v){
	   Intent i=new Intent(this, AcyPinyin.class);
	   this.startActivity(i);
   }

   
}
