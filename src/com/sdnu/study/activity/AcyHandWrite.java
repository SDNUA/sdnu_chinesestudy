package com.sdnu.study.activity;

import com.sdnu.study.fragment.FrmtBihuaTable;
import com.sdnu.study.fragment.FrmtCharTable;
import com.sdnu.study.fragment.FrmtShengmu;
import com.sdnu.study.fragment.FrmtYunmu;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class AcyHandWrite extends FragmentActivity implements OnClickListener {
	private TextView tvBihuaTable;
	private TextView tvCharTable;

	// fragment
	private FrmtBihuaTable frmtBihuaTable;
	private FrmtCharTable frmtCharTable;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acyhandwrite_layout);

		init();
	}

	private void init() {
		tvBihuaTable = (TextView) this.findViewById(R.id.tvBihuaTable);
		tvCharTable = (TextView) this.findViewById(R.id.tvCharTable);

		tvBihuaTable.setOnClickListener(this);
		tvCharTable.setOnClickListener(this);
		selectFragment(R.id.tvBihuaTable);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tvBihuaTable:
			resetTvColor();
			selectFragment(v.getId());
			break;

		case R.id.tvCharTable:
			resetTvColor();
			selectFragment(v.getId());
			break;

		default:
			break;
		}

	}

	private void selectFragment(int frmtid) {

		FragmentManager fm = this.getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		hideFragment(transaction);
		switch (frmtid) {
		case R.id.tvBihuaTable:
			if (frmtBihuaTable == null) {
				frmtBihuaTable = new FrmtBihuaTable();
				transaction.add(R.id.fl_chracterlist, frmtBihuaTable);
			} else {
				transaction.show(frmtBihuaTable);
			}

			tvBihuaTable.setBackgroundColor(this.getResources().getColor(
					R.color.pro_shenblue));
			break;

		case R.id.tvCharTable:
			if (frmtCharTable == null) {
				frmtCharTable = new FrmtCharTable();
				transaction.add(R.id.fl_chracterlist, frmtCharTable);
			} else {
				transaction.show(frmtCharTable);
			}
			tvCharTable.setBackgroundColor(this.getResources().getColor(
					R.color.pro_shenblue));
			break;

		default:
			break;
		}

		transaction.commit();
	}

	private void hideFragment(FragmentTransaction transaction) {

		if (frmtCharTable != null) {
			transaction.hide(frmtCharTable);
		}
		if (frmtBihuaTable != null) {
			transaction.hide(frmtBihuaTable);
		}
	}

	private void resetTvColor() {
		tvBihuaTable.setBackgroundColor(this.getResources().getColor(
				R.color.pro_white));
		tvCharTable.setBackgroundColor(this.getResources().getColor(
				R.color.pro_white));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onBackPressed()
	 */
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}

}
