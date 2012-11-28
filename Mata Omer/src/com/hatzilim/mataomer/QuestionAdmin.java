package com.hatzilim.mataomer;


import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
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
		
		if ( req.getParameter("action") != null ) {
			Long key = Long.parseLong(req.getParameter("key"));
			
			if ( req.getParameter("action").equalsIgnoreCase("delete") ) {
				// delete according to the key parameter
				ofy().delete().type(Question.class).id(key).now();
				
			} else if ( req.getParameter("action").equalsIgnoreCase("edit") ) {
				// display the question tree
				displayTree(key, req, resp);
				return;
			}
		}
		// just shows all the top level questions
		List<Question> questions = ofy().load().type(Question.class).limit(50).list();
		req.setAttribute("questions", questions);
		try { 
			getServletContext().getRequestDispatcher("/admin/view-questions.jsp").forward(req, resp); 
		} catch (ServletException e) {
			System.out.println (e.getMessage());
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		ObjectifyService.register(Question.class);
		ObjectifyService.register(Answer.class);
		
		
		if ( req.getParameter("question") != null ) {
			
			if ( req.getParameter("key") != null && req.getParameter("key").length() > 0 ) {
				// updating an existing question
				Long key = Long.valueOf( req.getParameter("key") );
				Question q = ofy().load().type(Question.class).id(key).get();
				q.setQuestion(req.getParameter("question"));
				
				// loop through answers and update them
				String[] answers = req.getParameterValues("option");
				int i = 0;
				for ( Answer a : q.getAnswers() ) {
					a.setAnswer(answers[i++]);
					ofy().save().entity(a).now();
				}
				
				// if we have more answers. add them
				Answer a;
				for (; i < answers.length; i++) {
					if (answers[i].trim().length() > 0) {
						a = new Answer (answers[i]);
						ofy().save().entity(a).now();
						q.addAnswer(a);
					}
				}
				ofy().save().entity(q).now();
				
				if (req.getParameter("answerkey") != null && req.getParameter("answerkey").length() > 0) {
					// means we are not just updating but adding an existing question
					// to a new answer
					q.setPrevious(Long.valueOf(req.getParameter("answerkey")));
				}

				// get the top question to show in the tree
//				displayTree(Long.valueOf(req.getParameter("topkey")), req, resp);
				resp.sendRedirect("/admin/process?action=edit&key=" + req.getParameter("topkey"));
			} else {
				// inserting a new question, either top level or next answer
				
				// first we create the question to add the answers
				Question q = new Question ( req.getParameter("question") );
				q.setOpenQuestion( req.getParameter("open_answer") != null );
				
				// now loop through the answers, persist each one, and add to q
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
				
				if (req.getParameter("answerkey") == null) { // new question
					// we added a top level question
//					displayTree(q.getId(), req, resp);
					resp.sendRedirect("/admin/process?action=edit&key=" + q.getId());
				} else {
					
					// we need to update the next field in the 'key' 
					q.setPrevious(Long.valueOf(req.getParameter("answerkey")));	
					
					// get the top question to show in the tree
					//displayTree(Long.valueOf(req.getParameter("topkey")), req, resp);
					resp.sendRedirect("/admin/process?action=edit&key=" + req.getParameter("topkey"));
				}
			}
			
			
		} 
		
	}
	
	
	private void displayTree (Long key, HttpServletRequest req, HttpServletResponse resp) throws IOException  {
		// display the question tree
		Question q = ofy().load().type(Question.class).id(key).get();
		req.setAttribute("q", q);
		try { 
			getServletContext().getRequestDispatcher("/admin/show-tree.jsp").forward(req, resp); 
		} catch (ServletException e) {
			System.out.println (e.getMessage());
		}
	}
	
}