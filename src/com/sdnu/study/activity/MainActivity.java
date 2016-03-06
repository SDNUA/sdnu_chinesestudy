package com.sdnu.study.activity;



import android.app.Activity;
import android.os.Bundle;

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

   
}
