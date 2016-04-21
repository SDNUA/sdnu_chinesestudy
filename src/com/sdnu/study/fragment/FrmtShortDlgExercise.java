package com.sdnu.study.fragment;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sdnu.study.activity.R;
import com.sdnu.study.config.MyConfig;
import com.sdnu.study.domain.ShortDialogItem;
import com.sdnu.study.myUtils.DBUtils;
import com.sdnu.study.myview.FixGridLayout;

public class FrmtShortDlgExercise extends Fragment {
	private View view = null;
	private Context context;

	private Button btnReset;
	private TextView tvPinyinSentence;
	private TextView tvHanSentence;
	private FixGridLayout llWords;// 连词成句
	private StringBuffer sb = null;
	private String rightSen = null;
	private int len = 0;
	private int key = 1;
	private int count = 0;
	private int num = 0;
	private int size = 0;
	private DBUtils dbUtil = null;

	private List<ShortDialogItem> dataDlg = null;

	boolean flag = false;// 判断是否成功

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.frmtexercise_layout, null);
		context = view.getContext();
		loadData();
		init();
		return view;
	}

	private void init() {

		tvPinyinSentence = (TextView) view.findViewById(R.id.tvPinyinSentence);
		tvHanSentence = (TextView) view.findViewById(R.id.tvHanSentence);
		llWords = (FixGridLayout) view.findViewById(R.id.llWords);
		llWords.setmCellHeight(dip2px(getContext(),50));  
		llWords.setmCellWidth(dip2px(getContext(),50)); 
		btnReset = (Button) view.findViewById(R.id.btnReset);
		btnReset.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				again();
			}
		});
		//show(llWords);
		nextExe();
	}

	/**
	 * 加载数据库
	 */
	private void loadData() {
		Intent intent = getActivity().getIntent();
		key = intent.getIntExtra("key", 1);
		dbUtil = DBUtils.getInstance(context);
		dbUtil.openMyDb(MyConfig.DB_NAME);
		dataDlg = dbUtil.getDialogData(key);
		size = dataDlg.size();
		dbUtil.close(MyConfig.DB_NAME);

	}
	
	/**
	 * again
	 */
	private void again() {
		if (size > num && num >= 0){
			reset(num);
		}else{
			num--;
			reset(num);
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
//			FrmtShortDlgWordsExercise frmt=new FrmtShortDlgWordsExercise();
//			switchContent(this, frmt);
		}
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
	private void initLL(int loc) {

		String strPy = dataDlg.get(loc).getPinyin();
		tvPinyinSentence.setText(strPy);
		
		rightSen = dataDlg.get(loc).getChinese();
		//rightSen =rightSen.substring(0,rightSen.length()-1);
		//System.out.println(rightSen);
		Character[] words = getWords(rightSen);
		len = words.length;
		if (llWords != null) {
			TextView tv = null;
			for (Character word : words) {
				TextView text = getTextView(word.toString());
				llWords.addView(text);
			}
		}
	}

	
	
	/**
	 * 功能:初始化textview
	 * 
	 * @param str
	 * @return
	 */
	private TextView getTextView(final String str) {
		final TextView tv = new TextView(view.getContext());
		tv.setPadding(10, 10, 10, 10);
		tv.setBackgroundResource(R.drawable.blue_border);
		tv.setText(str);
		tv.setTextSize(25);
		tv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				tv.setVisibility(TextView.GONE);
				textOnclick(str);
			}

		});
		return tv;
	}

	/*
	 * 恢复初始状态
	 */
	private void reset(int i) {
		tvHanSentence.setText("连词成句");
		sb = null;
		count = 0;
		rightSen = null;
		llWords.removeAllViews();
		initLL(i);
	}

	/**
	 * 点击事件
	 * 
	 * @param str
	 */
	private void textOnclick(String str) {

		if (sb == null) {
			sb = new StringBuffer();
		}
		if (sb.length() < len) {
			sb.append(str);
			tvHanSentence.setText(sb);
			count++;

		}
		if (rightSen.equals(sb.toString())) {
			num++;
			nextExe();
			return;
		} else {
			if (count == len) {
				Toast.makeText(getContext(), "失败，哈哈哈！", 1).show();
				again();
			}
		}
	}

	/**
	 * 句子转换字符数组
	 * 
	 * @param words
	 * @return
	 */

	private Character[] getWords(String words) {
		words = shuff(words);
		char charsArr[] = words.toCharArray();
		Character[] chars = new Character[words.length()];
		int count = 0;
		for (char c : charsArr) {
			chars[count] = new Character(c);
			count++;
		}
		return chars;
	}

	/**
	 * 打乱字符串顺序
	 * 
	 * @param str
	 * @return
	 */
	private String shuff(String str) {
		String in = new String(str);
		List<String> list = Arrays.asList(in.split(""));
		Collections.shuffle(list);
		String out = new String();
		for (String s : list) {
			out += s;
		}
		return out;
	}
	
	 public int dip2px(Context context, float dpValue) {
		  final float scale = context.getResources().getDisplayMetrics().density;
		  return (int) (dpValue * scale + 0.5f);
		 } 

}
