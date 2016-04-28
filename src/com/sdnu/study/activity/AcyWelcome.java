package com.sdnu.study.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class AcyWelcome extends Activity{
	protected boolean _active = true;
    protected int _splashTime = 3000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acywelcome_layout);
		
		setDelay();
	}

	private void setDelay() {
		Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while(_active && (waited < _splashTime)) {
                        sleep(100);
                        if(_active) {
                            waited += 100;
                        }
                    }
                } catch(InterruptedException e) {
               	 throw new RuntimeException(e);
                } finally {
                   redirectTo();
                }
            }

        };
        splashTread.start();
		
	}
	
	private void redirectTo() {
		Intent i=new Intent(this,MainActivity.class);
		this.startActivity(i);
		finish();
		
	}

}
