package com.sdnu.study.myUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyJson {
	
	
	public static void jsonToObj(String str){
		
		try {
			JSONObject jsonObject = new JSONObject(str);
			System.out.println(jsonObject.get("stats").toString()+jsonObject.get("msg").toString());
			if(jsonObject.get("stats").toString().equals("200")){
				JSONArray arrJson=jsonObject.getJSONArray("data");
				System.out.println("id::"+arrJson.get(0));
			}
			
			
		} catch (JSONException e) {
			e.printStackTrace();
		}  
		
		
	}

}
