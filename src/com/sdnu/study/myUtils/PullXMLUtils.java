package com.sdnu.study.myUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

import com.sdnu.study.domain.PinyinTableItem;
import com.sdnu.study.domain.Version;

public class PullXMLUtils {

	public static List<PinyinTableItem> parse(InputStream is) throws Exception {

		List<PinyinTableItem> list = null;
		PinyinTableItem item = null;
		// 使用Android提供的实用工具类android.util.Xml
		XmlPullParser parser = Xml.newPullParser();
		// 解析文件输入流
		parser.setInput(is, "utf-8");

		// 产生第一个事件
		int eventType = parser.getEventType();

		// 只要不是文档结束事件，就一直循环
		while (eventType != XmlPullParser.END_DOCUMENT) {

			switch (eventType) {
			// 触发开始文档事件
			case XmlPullParser.START_DOCUMENT:
				list = new ArrayList<PinyinTableItem>();
				break;
			// 触发开始元素事件
			case XmlPullParser.START_TAG:
				String name = parser.getName();
				if (name.equals("item")) {
					item = new PinyinTableItem();
					item.setId(parser.getAttributeValue(0));
				}
				if (item != null) {
					if (name.equals("mchar")) {
						item.setmChar(parser.nextText());
					}
					if (name.equals("mchar_fy")) {
						item.setmCharFy(parser.nextText());
					}
					if (name.equals("pic")) {
						item.setPic(parser.nextText());
					}
					if (name.equals("hanzi_1")) {
						item.setHanziFirst(parser.nextText());
					}
					if (name.equals("hanzi_2")) {
						item.setHanziSecond(parser.nextText());
					}
					if (name.equals("hanzi_3")) {
						item.setHanziThird(parser.nextText());
					}
					if (name.equals("hanzi_4")) {
						item.setHanziForth(parser.nextText());
					}
					if (name.equals("hanzi_1_fy")) {
						item.setHanziFirstFy(parser.nextText());
					}
					if (name.equals("hanzi_2_fy")) {
						item.setHanziSecondFy(parser.nextText());
					}
					if (name.equals("hanzi_3_fy")) {
						item.setHanziThirdFy(parser.nextText());
					}
					if (name.equals("hanzi_4_fy")) {
						item.setHanziForthFy(parser.nextText());
					}
					if (name.equals("hanzi_1_py")) {
						item.setHanziFirstPy(parser.nextText());
					}
					if (name.equals("hanzi_2_py")) {
						item.setHanziSecondPy(parser.nextText());
					}
					if (name.equals("hanzi_3_py")) {
						item.setHanziThirdPy(parser.nextText());
					}
					if (name.equals("hanzi_4_py")) {
						item.setHanziForthPy(parser.nextText());
					}

				}
				break;

			// 触发结束元素事件
			case XmlPullParser.END_TAG:
				if (item != null) {
					if (parser.getName().equals("item")) {
						list.add(item);
						item = null;
					}
				}
				break;
			default:
				break;

			}
			eventType = parser.next();
		}

		return list;
	}

	public static Version parseVersion(InputStream is)
			throws XmlPullParserException, NumberFormatException, IOException {
		Version v = null;
		XmlPullParser parser = Xml.newPullParser();
		// 解析文件输入流
		parser.setInput(is, "utf-8");
		// 产生第一个事件
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			if (XmlPullParser.START_DOCUMENT == eventType) {
				v = new Version();
			}
			if (XmlPullParser.START_TAG == eventType) {
				String name = parser.getName();
				if (name != null) {
					if (name.equals("name")) {
						v.setName(parser.nextText());
					}
					if (name.equals("version")) {
						int code = Integer
								.parseInt(parser.getAttributeValue(0));
						v.setVersionCode(code);
						v.setVersionMsg(parser.nextText());
					}
				}
			}
			eventType = parser.next();
		}
		System.out.println(v.toString());
		return v;

	}
}
