package com.sdnu.study.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.sdnu.study.fragment.FrmtShengmu;
import com.sdnu.study.fragment.FrmtYunmu;

public class AcyPinyin extends FragmentActivity implements OnClickListener {
	
	
	
	private  TextView tvYunmu;
	private  TextView tvShengmu;
	
	
	//fragment
	private FrmtShengmu frmShengmu;
	private FrmtYunmu frmYunmu;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pinyinbiao);
		
		init();
			
	}
	
	private void init() {
		
		tvYunmu=(TextView) this.findViewById(R.id.tvYunmubiao);
		tvShengmu=(TextView) this.findViewById(R.id.tvShengmubiao);
		
		tvYunmu.setOnClickListener(this);
		tvShengmu.setOnClickListener(this);
		selectFragment(R.id.tvYunmubiao);
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tvYunmubiao:
			resetTvColor();
			selectFragment(v.getId());
			break;
			
		case R.id.tvShengmubiao:
			resetTvColor();
			selectFragment(v.getId());
			break;

		default:
			break;
		}
	}
	
	private void selectFragment(int frmtid) {
		
		FragmentManager fm=this.getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		hideFragment(transaction);
		switch (frmtid) {
		case R.id.tvYunmubiao:
			if(frmYunmu==null){
				frmYunmu=new FrmtYunmu();
				transaction.add(R.id.fl_chracterlist,frmYunmu);
			}else{
				transaction.show(frmYunmu);
			}
			
			tvYunmu.setBackgroundColor(this.getResources().getColor(R.color.pro_shenblue));
			break;
			
		case R.id.tvShengmubiao:
			if(frmShengmu==null){
				frmShengmu=new FrmtShengmu();
				transaction.add(R.id.fl_chracterlist, frmShengmu);
			}else{
				transaction.show(frmShengmu);
			}
			tvShengmu.setBackgroundColor(this.getResources().getColor(R.color.pro_shenblue));
			break;

		default:
			break;
		}
		
		transaction.commit();
	}

	private void hideFragment(FragmentTransaction transaction) {
		
		if(frmYunmu!=null){
			transaction.hide(frmYunmu);
		}
		if(frmShengmu!=null){
			transaction.hide(frmShengmu);
		}
	}

	private void resetTvColor() {
		tvYunmu.setBackgroundColor(this.getResources().getColor(R.color.pro_white));
		tvShengmu.setBackgroundColor(this.getResources().getColor(R.color.pro_white));
	}

}
