package com.sdnu.study.adapter;

import java.util.List;

import com.sdnu.study.activity.R;
import com.sdnu.study.domain.ExerciseAnswerItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ExerciseAnswerItemAdapter extends BaseAdapter {
	private List<ExerciseAnswerItem> list;
	private LayoutInflater mInflater;
	public ExerciseAnswerItemAdapter(Context context,List<ExerciseAnswerItem> list) {
		mInflater=LayoutInflater.from(context);
		this.list=list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView=mInflater.inflate(R.layout.frmtexercisewords_item_layout, null);
		TextView tvAnswer=(TextView) convertView.findViewById(R.id.tvAnswer);
		TextView tvFlag=(TextView) convertView.findViewById(R.id.tvAnswerFlag);
		ExerciseAnswerItem eai=list.get(position);
		tvFlag.setText(eai.getAnswerFlag());
		tvAnswer.setText(eai.getAnswer());
		return convertView;
	}

}
