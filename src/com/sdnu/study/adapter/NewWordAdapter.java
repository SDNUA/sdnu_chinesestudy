package com.sdnu.study.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdnu.study.activity.R;
import com.sdnu.study.domain.NewWord;

public class NewWordAdapter extends BaseAdapter {
	List<NewWord> list;
	private LayoutInflater mInflater;
	public NewWordAdapter(Context context,List<NewWord> list) {
		this.list=list;
		mInflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder=null;
		if(convertView==null){
			convertView=mInflater.inflate(R.layout.frmtnewwords_item_layout, null);
			holder=new Holder();
			holder.tvHanChar=(TextView) convertView.findViewById(R.id.tvHanziFirst);
			holder.tvPinyinChar=(TextView) convertView.findViewById(R.id.tvHanziFirstPy);
			holder.tvEnglish=(TextView) convertView.findViewById(R.id.tvEnglish);
			
			holder.tvHanChar.setText(list.get(position).getHanChar());
			holder.tvPinyinChar.setText(list.get(position).getPinyinChar());
			holder.tvEnglish.setText(list.get(position).getEnglishChar());
			convertView.setTag(holder);
		}else{
			holder=(Holder) convertView.getTag();
		}
		
		return convertView;
	}
	
	static class Holder{
		public TextView tvHanChar;
		public TextView tvPinyinChar;
		public TextView tvEnglish;
		public ImageView ivFayion;
	}

}
