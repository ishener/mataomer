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
		ObjectifyService.register(Question.class);
		ObjectifyService.register(Answer.class);
		
		resp.setContentType("text/plain");
		
		if ( req.getParameter("question") != null ) {
			
			// first we create the question to add the answers
			Question q = new Question ( req.getParameter("question") );
			q.setOpenQuestion( req.getParameter("open_answer") != null );
			
			// not loop through the answers, persist each one, and add to q
			String[] answers = req.getParameterValues("option");
			Answer a;
			for( String answer : answers) {
				if (answer != null  && answer.trim().length() > 0) {
					a = new Answer (answer);
					ofy().save().entity(a).now();
					q.addAnswer(a);
				}
			}
			
			// lastly save the question
			ofy().save().entity(q).now();
			resp.getWriter().println( "successfully persisten question id: " + q.getId() );
			// TODO: check errors with objectify
		} 
		
	}
}