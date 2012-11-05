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
		ObjectifyService.register(Answer.class);
		
		resp.setContentType("text/plain");
		
		Answer a = new Answer ("answer 3");
		ofy().save().entity(a).now();
		
		Question q = new Question ("the text for the q");
		q.addAnswer(a);
		ofy().save().entity(q).now();
		
//		Question q = ofy().load().type(Question.class).id(1).get();
		resp.getWriter().println( a.getKey() );
	}
}
