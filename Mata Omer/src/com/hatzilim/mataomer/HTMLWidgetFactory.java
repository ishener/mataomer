package com.hatzilim.mataomer;


class HTMLWidgetFactory {
	public static String createButton (String cls, String value, String id) {
		return "<input type=\"button\" class=\"" + cls + "\" value=\"" + value + "\" key=\"" + id + "\" />";
	}
	
	public static String createImg (String cls, String src) {
		return "<img class=\"" + cls + "\" src=\"" + src + "\" />";
	}
	
	public static String createFormButton (String cls, String value, String url, String... hiddens) {
		StringBuilder strBuilder = new StringBuilder("<form method=\"post\" action=\"" + url + "\">");
		strBuilder.append("<input type=\"submit\" class=\"" + cls + "\" value=\"" + value + "\" />");
		
		for (int i = 0; i < hiddens.length; i++) {
			strBuilder.append("<input type=\"hidden\" name=\"" + hiddens[i] + "\" value=\"" + hiddens[++i] + "\" />");
		}
		strBuilder.append("</form>");
		return strBuilder.toString();
	}
}
