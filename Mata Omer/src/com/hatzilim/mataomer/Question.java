package com.hatzilim.mataomer;


import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

@Entity
public class Question {
	@Id Long id;
	@Index String question;
	@Index Boolean openQuestion;
	@Index @Load List<Ref<Answer>> answers = new ArrayList<Ref<Answer>>();
	@Index List<String> tags = new ArrayList<String>();
	
	public Question () {
		
	}
	
	public Question (String arg) {
		this.question = arg;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}
	public void setQuestion(String arg) {
		this.question = arg;
	}

	public Boolean getOpenQuestion() {
		return openQuestion;
	}
	public void setOpenQuestion(Boolean openQuestion) {
		this.openQuestion = openQuestion;
	}
	
	com.googlecode.objectify.Key<Question> getKey() {
        return com.googlecode.objectify.Key.create(Question.class, id); 
    }
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public void addTag(String tag) {
		tags.add(tag);
	}
	
	public void addAnswer ( Answer ans ) {
		answers.add ( Ref.create(ans.getKey(), ans) );
	}

	public List<Answer> getAnswers() {
		List<Answer> realAnswers = new ArrayList<Answer>();
		for ( Ref<Answer> a : answers ) {
			realAnswers.add(a.get());
		}
		return realAnswers;
	}

	public void setAnswers(List<Ref<Answer>> answers) {
		this.answers = answers;   
	}
	
	public void setPrevious (Long answerKey) {
		// we need to update the next field in the 'key' 
		Answer topAnswer = ofy().load().type(Answer.class).id(answerKey).get();
		topAnswer.setNext(this);
		ofy().save().entity(topAnswer).now();
	}
	
	public String getOutput () {
		StringBuilder strBuilder = new StringBuilder("<div class=\"qNode\" key=\"" + this.id + "\">");
		strBuilder.append("<p class=\"qp\"><a key=\"" + this.id + "\">" + this.question + "</a> " +
						HTMLWidgetFactory.createImg( "line-img", "/static/images/line-2411.png" ) +
						HTMLWidgetFactory.createButton( "edit-q", "", this.id.toString() ) +
						HTMLWidgetFactory.createButton( "remove-q", "", this.id.toString() ) 
//						HTMLWidgetFactory.createFormButton( "remove-q", "",  "/admin/process", 
//								"key", this.id.toString(), "action", "remove" ) 
						  + "</p>");
		for ( Answer a : getAnswers() ) {
			strBuilder.append( a.getOutput() );
		}
		strBuilder.append("</div>");
		return strBuilder.toString();
	}
	
	public Map<Long, String> getAllQuestions () {
		Map<Long, String> all = new HashMap<Long, String>();
		all.put(this.id, this.question);
		for ( Answer a : getAnswers() ) {
			if (a.getNext() != null)
				all.putAll( a.getNext().getAllQuestions() );
		}
		return all;
	}
	
	
	
}

