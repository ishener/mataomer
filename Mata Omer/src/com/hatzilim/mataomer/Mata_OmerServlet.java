package com.hatzilim.mataomer;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.ObjectifyService;

@SuppressWarnings("serial")
public class Mata_OmerServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		ObjectifyService.register(Question.class);
		ObjectifyService.register(Answer.class);
		
		resp.setContentType("text/plain");
		
//		Answer a = new Answer ("answer 3");
//		ofy().save().entity(a).now();
//		
//		Question q = new Question ("the text for the q");
//		q.addAnswer(a);
//		ofy().save().entity(q).now();
		
		List<Answer> as = ofy().load().type(Answer.class).filter("next", com.googlecode.objectify.Key.create(Question.class, 177) ).list();
		
		for (Answer a : as) {
			resp.getWriter().println( a.getAnswer() );
			a.setNext(null);
			ofy().save().entity(a).now();
		}
		
		
		
	}
}
