package com.hatzilim.mataomer;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;


@SuppressWarnings("serial")
public class Mata_OmerServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		ObjectifyService.register(Question.class);
		ObjectifyService.register(Answer.class);
		ObjectifyService.register(User.class);
		
		UserService userService = UserServiceFactory.getUserService();

        String thisURL = req.getRequestURI();

        resp.setContentType("text/html");
        if (req.getUserPrincipal() != null) {
        	String id = userService.getCurrentUser().getUserId();
        	User user = ofy().load().type(User.class).id(id).get();
        	if (user == null) {
        		// we have a brand new user
        		user = new User (userService.getCurrentUser().getEmail(), id);
        	}
        	user.setLastSeen(new Date());
        	ofy().save().entity(user).now();
        	
            resp.getWriter().println("<p>You can <a href=\"" +
                                     userService.createLogoutURL(thisURL) +
                                     "\">sign out</a>.</p>");
        } else {
            resp.getWriter().println("<p>Please <a href=\"" +
                                     userService.createLoginURL(thisURL) +
                                     "\">sign in</a>.</p>");
        }		
		
		
		
	}
}
