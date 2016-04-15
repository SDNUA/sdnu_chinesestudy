package com.sdnu.study.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.sdnu.study.fragment.FrmtShortDlg;
import com.sdnu.study.fragment.FrmtShortDlgExercise;
import com.sdnu.study.fragment.FrmtShortDlgNewWords;

public class AcyShortDlg extends FragmentActivity  implements OnClickListener {
	
	private TextView tvDlg;
	private TextView tvExercise;
	private TextView tvNewWords;
	private TextView tvTitle;
	
	//fragment
	private FrmtShortDlg frmtshortdlg;
	private FrmtShortDlgExercise frmtexercise;
	private FrmtShortDlgNewWords frmtnewwords;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acyshortdialog);
		init();
		
	}
	private void init() {
		tvDlg=(TextView) this.findViewById(R.id.tvShortDlg);
		tvNewWords=(TextView) this.findViewById(R.id.tvNewWords);
		tvExercise=(TextView) this.findViewById(R.id.tvExercise);
		tvTitle=(TextView) this.findViewById(R.id.tvTitle);
		
		tvDlg.setOnClickListener(this);
		tvNewWords.setOnClickListener(this);
		tvExercise.setOnClickListener(this);
		
		Intent intent=getIntent();
		String title=intent.getStringExtra("title");
		tvTitle.setText(title);
		selectFragment(R.id.tvShortDlg);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tvShortDlg:
			resetTvColor();
			selectFragment(v.getId());
			break;
			
		case R.id.tvNewWords:
			resetTvColor();
			selectFragment(v.getId());
			break;
			
		case R.id.tvExercise:
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
		case R.id.tvShortDlg:
			if(frmtshortdlg==null){
				frmtshortdlg=new FrmtShortDlg();
				transaction.add(R.id.fl_chracterlist,frmtshortdlg);
			}else{
				transaction.show(frmtshortdlg);
			}
			
			tvDlg.setBackgroundColor(this.getResources().getColor(R.color.pro_shenblue));
			break;
			
		case R.id.tvNewWords:
			if(frmtnewwords==null){
				frmtnewwords=new FrmtShortDlgNewWords();
				transaction.add(R.id.fl_chracterlist, frmtnewwords);
			}else{
				transaction.show(frmtnewwords);
			}
			tvNewWords.setBackgroundColor(this.getResources().getColor(R.color.pro_shenblue));
			break;
		case R.id.tvExercise:
			if(frmtexercise==null){
				frmtexercise=new FrmtShortDlgExercise();
				transaction.add(R.id.fl_chracterlist, frmtexercise);
			}else{
				transaction.show(frmtexercise);
			}
			tvExercise.setBackgroundColor(this.getResources().getColor(R.color.pro_shenblue));
			break;

		default:
			break;
		}
		
		transaction.commit();
	}

	private void hideFragment(FragmentTransaction transaction) {
		
		if(frmtshortdlg!=null){
			transaction.hide(frmtshortdlg);
		}
		if(frmtnewwords!=null){
			transaction.hide(frmtnewwords);
		}
		if(frmtexercise!=null){
			transaction.hide(frmtexercise);
		}
	}

	private void resetTvColor() {
		tvExercise.setBackgroundColor(this.getResources().getColor(R.color.pro_white));
		tvNewWords.setBackgroundColor(this.getResources().getColor(R.color.pro_white));
		tvDlg.setBackgroundColor(this.getResources().getColor(R.color.pro_white));
	}
	
	
	

}
