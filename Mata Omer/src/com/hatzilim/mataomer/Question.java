package com.hatzilim.mataomer;


import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Question {
	@Id Long id;
	String question;
//	private final List<Answer> answers = new ArrayList<Answer>();
	
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
	
//	public void addAnswer ( String ans ) {
//		answers.add (new Answer(ans, 0));
//	}
//	public List<Answer> getAnswers () {
//		return this.answers;
//	}
	
}

