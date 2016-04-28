package com.sdnu.study.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class AcyLogin extends Activity {
	
	private TextView tvRegister=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acylogin_layout);
		initViews();
		
		
	}
	private void initViews() {
		tvRegister=(TextView) this.findViewById(R.id.tvRegister);
		tvRegister.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(AcyLogin.this,AcyRegister.class);
				AcyLogin.this.startActivity(intent);
			}
		});
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onBackPressed()
	 */
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}

}
