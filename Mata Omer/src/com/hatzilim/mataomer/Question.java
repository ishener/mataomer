package com.hatzilim.mataomer;


import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Question {
	@Id Long id;
	@Index String question;
	Boolean openQuestion;
	List<Ref<Answer>> answers = new ArrayList<Ref<Answer>>();
	
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
	
	public void addAnswer ( Answer ans ) {
		System.out.println("adding this key: " + ans.getKey());
		answers.add ( Ref.create(ans.getKey(), ans) );
	}
//	public List<Answer> getAnswers () {
//		return this.answers;
//	}
	
}

