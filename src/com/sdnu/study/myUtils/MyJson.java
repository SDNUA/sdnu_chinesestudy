package com.sdnu.study.myUtils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sdnu.study.domain.PinyinTableItem;

public class MyJson {

	public static List<PinyinTableItem> jsonToObj(String str) {
		List<PinyinTableItem> list=new ArrayList<PinyinTableItem>();
		try {
			JSONObject jsonObject = new JSONObject(str);
			System.out.println(jsonObject.get("stats").toString()
					+ jsonObject.get("msg").toString());
			int stats = Integer.parseInt(jsonObject.get("code").toString());
			if (stats == 200) {
				JSONArray arrJson = jsonObject.getJSONArray("data");
				for (int i = 0; i < arrJson.length(); i++) {
					JSONObject obj = arrJson.getJSONObject(i);
					PinyinTableItem pti = new PinyinTableItem();
					pti.setmChar(obj.getString("pinyin"));
					list.add(pti);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}

}
