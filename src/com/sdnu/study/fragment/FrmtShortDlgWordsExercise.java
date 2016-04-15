package com.sdnu.study.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.sdnu.study.activity.AcyShortDlg;
import com.sdnu.study.activity.R;
import com.sdnu.study.adapter.DlgListAdapter;
import com.sdnu.study.adapter.NewWordAdapter;
import com.sdnu.study.config.MyConfig;
import com.sdnu.study.domain.NewWord;
import com.sdnu.study.domain.ShortDialogItem;
import com.sdnu.study.myUtils.DBUtils;

public class FrmtShortDlgWordsExercise extends Fragment {
	private View view = null;
	private Context context;

	private Button btnReset;
	private TextView tvAnswerA;
	private TextView tvAnswerB;
	private TextView tvAnswerC;
	private TextView tvWord;
	private int len = 0;
	private int key = 1;
	private int num = 0;
	private int size = 0;
	private DBUtils dbUtil = null;

	private List<NewWord> dataWords = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view=inflater.inflate(R.layout.frmtexercisewords_layout,null);
		context=view.getContext();
		loadData();
		init();
		return view;
	}
	/**
	 * 加载数据库
	 */
	private void loadData() {
		Intent intent = getActivity().getIntent();
		key = intent.getIntExtra("key", 1);
		dbUtil = DBUtils.getInstance(context);
		dbUtil.openMyDb(MyConfig.DB_NAME);
		dataWords = dbUtil.getWordsData(key);
		size = dataWords.size();
		dbUtil.close(MyConfig.DB_NAME);

	}
	private void init() {
		tvWord=(TextView) view.findViewById(R.id.tvWord);
		tvAnswerA=(TextView) view.findViewById(R.id.tvAnswerA);
		tvAnswerB=(TextView) view.findViewById(R.id.tvAnswerB);
		tvAnswerC=(TextView) view.findViewById(R.id.tvAnswerC);
		btnReset= (Button) view.findViewById(R.id.btnReset);
		btnReset.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				again();
			}
		});
		nextExe();
	}
	/**
	 * again
	 */
	private void again() {
		if (size > num && num >= 0){
			
		}else{
			num--;
			
		}
		
	}
	/**
	 * 点击下一个
	 */
	private void nextExe() {
		if (size > num && num >= 0) {
			reset(num);
		}else{
			Toast.makeText(view.getContext(), "完成了练习", 1).show();
			FrmtShortDlgWordsExercise frmt=new FrmtShortDlgWordsExercise();
			switchContent(this, frmt);
		}
	}
	/**
	 * 
	 */
	private void reset(int i) {
		NewWord nw=dataWords.get(i);
		tvWord.setText(nw.getHanChar());
		
	}
	/**
	 * 切换fragment
	 * @param from
	 * @param to
	 */
	private void switchContent(Fragment from, Fragment to) {
        if (from != to) {
        	FragmentManager fm = getFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            if (!to.isAdded()) {    // 先判断是否被add过
                transaction.hide(from).add(R.id.fl_chracterlist, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
        }
    }
	
	/**
	 * 产生随机数
	 * @param len
	 * @return
	 */
	private int getRandomNum(int len,int i){
		int rand=-1;
		while(rand!=i){
			double num=Math.random();//0~1
			rand=(int) Math.floor(len*num);
		}
		return rand;
	}

}
