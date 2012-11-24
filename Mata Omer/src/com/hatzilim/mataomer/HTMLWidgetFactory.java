package com.hatzilim.mataomer;

class HTMLWidgetFactory {
	public static String createButton (String cls, String value, String id) {
		return "<input type=\"button\" class=\"" + cls + "\" value=\"" + value + "\" key=\"" + id + "\" />";
	}
	public static String createImg (String cls, String src) {
		return "<img class=\"" + cls + "\" src=\"" + src + "\" />";
	}
}
