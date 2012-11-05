package com.hatzilim.mataomer;


import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.ObjectifyService;

@SuppressWarnings("serial")
public class QuestionAdmin extends HttpServlet {
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

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
//		ObjectifyService.register(Question.class);
//		ObjectifyService.register(Answer.class);
//		
//		resp.setContentType("text/plain");
//		
//		Answer a = new Answer ("answer 3");
//		ofy().save().entity(a).now();
//		
//		Question q = new Question ("the text for the q");
//		q.addAnswer(a);
//		ofy().save().entity(q).now();
//		
////		Question q = ofy().load().type(Question.class).id(1).get();
//		resp.getWriter().println( a.getKey() );
		
		Map params = req.getParameterMap();
		Iterator i = params.keySet().iterator();

		while ( i.hasNext() )
		  {
		    String key = (String) i.next();
		    String value = ((String[]) params.get( key ))[ 0 ];
		    System.out.println(key + ": " + value);
		  }
		
		String[] names = req.getParameterValues("option");
		for( String name : names) {
			   System.out.println(name);
			}
	}
}