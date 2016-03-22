package com.sdnu.study.activity;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.sdnu.study.domain.PinyinTableItem;
import com.sdnu.study.myUtils.DBUtils;
import com.sdnu.study.myview.MySlidingMenu;


public class MainActivity extends Activity {
	private MySlidingMenu slidingMenu;
	private TextView tvRumen; 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
      //侧滑菜单
    	slidingMenu=new MySlidingMenu(this);
    	slidingMenu.showSlidingMenu();
    	init();
    	
    }
    
    private void init() {
    	tvRumen=(TextView) this.findViewById(R.id.tvPinyinbiao);
    	tvRumen.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i=new Intent(MainActivity.this, AcyPinyin.class);
				MainActivity.this.startActivity(i);				
			}
		});
	}

	private void setData() {
		// TODO Auto-generated method stub
    	DBUtils utils=DBUtils.getInstance(this);
		utils.open();
	
		//utils.insert("pinyin", arrStr);
		utils.close();
	}
    
   
   
}
