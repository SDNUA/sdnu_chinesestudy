package com.sdnu.study.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdnu.study.activity.R;
import com.sdnu.study.domain.NewWord;
import com.sdnu.study.interfaces.IOnClick;
import com.sdnu.study.interfaces.IOnItemOnclick;

public class NewWordAdapter extends BaseAdapter {
	List<NewWord> list;
	private LayoutInflater mInflater;
	IOnItemOnclick onclick;
	public NewWordAdapter(Context context,List<NewWord> list,IOnItemOnclick onclick) {
		this.list=list;
		this.onclick=onclick;
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		Holder holder=null;
		if(convertView==null){
			convertView=mInflater.inflate(R.layout.frmtnewwords_item_layout, null);
			holder=new Holder();
			holder.tvHanChar=(TextView) convertView.findViewById(R.id.tvHanziFirst);
			holder.tvPinyinChar=(TextView) convertView.findViewById(R.id.tvHanziFirstPy);
			holder.tvEnglish=(TextView) convertView.findViewById(R.id.tvEnglish);
			holder.ivFayin=(ImageView)convertView.findViewById(R.id.ivNewWordsFy);
			
			holder.tvHanChar.setText(list.get(position).getHanChar());
			holder.tvPinyinChar.setText(list.get(position).getPinyinChar());
			holder.tvEnglish.setText(list.get(position).getEnglishChar());
			holder.ivFayin.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					onclick.onclick(position);
				}
			});
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
		public ImageView ivFayin;
	}

}
