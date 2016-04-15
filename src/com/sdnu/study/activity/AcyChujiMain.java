package com.sdnu.study.activity;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.sdnu.study.config.MyConfig;
import com.sdnu.study.myUtils.DBUtils;

public class AcyChujiMain extends Activity implements OnItemClickListener {

	private GridView gv;
	List<Map<String, String>> data = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acychujimain_layout);
		load();
		SimpleAdapter sa = new SimpleAdapter(this, data,
				R.layout.acychujimain_item_layout, new String[] { "title" },
				new int[] { R.id.tvDlgMainItemTitle });
		gv = (GridView) this.findViewById(R.id.gvDlgMain);
		gv.setAdapter(sa);
		gv.setOnItemClickListener(this);
	}
	/**
	 * 加载数据库信息
	 */
	private void load() {
		DBUtils dbUtil = DBUtils.getInstance(this);
		dbUtil.openMyDb(MyConfig.DB_NAME);
		data = dbUtil.getCourseTitle();
		dbUtil.close(MyConfig.DB_NAME);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent i = new Intent(AcyChujiMain.this, AcyShortDlg.class);
		i.putExtra("key", position + 1);
		Map<String, String> map = data.get(position);
		i.putExtra("title", map.get("title"));
		this.startActivity(i);
	}

}
