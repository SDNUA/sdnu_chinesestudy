package com.sdnu.study.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sdnu.study.activity.R;
import com.sdnu.study.adapter.ExerciseAnswerItemAdapter;
import com.sdnu.study.config.MyConfig;
import com.sdnu.study.domain.ExerciseAnswerItem;
import com.sdnu.study.domain.NewWord;
import com.sdnu.study.myUtils.DBUtils;

public class FrmtShortDlgWordsExercise extends Fragment {
	private View view = null;
	private Context context;

	//private Button btnReset;
	private TextView tvWord;
	private ListView lvExerciseAnswerItem;
	private int len = 0;
	private int key = 1;
	private int num = 0;
	private int size = 0;
	private DBUtils dbUtil = null;

	private List<ExerciseAnswerItem> dataWords = null;

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
		dataWords = dbUtil.getExerciseData(key);
		size = dataWords.size();
		dbUtil.close(MyConfig.DB_NAME);
		
		

	}
	private void init() {
		tvWord=(TextView) view.findViewById(R.id.tvWord);
		lvExerciseAnswerItem= (ListView) view.findViewById(R.id.lvExerciseAnswerItem);
			
		ExerciseAnswerItemAdapter adapter=new ExerciseAnswerItemAdapter(context, dataWords);
		lvExerciseAnswerItem.setAdapter(adapter);

	}
	

}
