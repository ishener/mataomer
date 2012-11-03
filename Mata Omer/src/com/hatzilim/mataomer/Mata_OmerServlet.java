package com.hatzilim.mataomer;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.ObjectifyService;
import static com.googlecode.objectify.ObjectifyService.ofy;

@SuppressWarnings("serial")
public class Mata_OmerServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		ObjectifyService.register(Question.class);
//		ObjectifyService.register(Answer.class);
		
		resp.setContentType("text/plain");
		
		Question q = new Question ("the text for the q");
		ofy().save().entity(q).now();
		resp.getWriter().println("changeds");
	}
}
