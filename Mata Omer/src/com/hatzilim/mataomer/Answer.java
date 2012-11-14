package com.hatzilim.mataomer;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Answer {
	@Id Long id;
	@Index String answer;
	Ref<Question> next;
	
	public Answer () { }
	
	public Answer (String ans) {
		this.answer = ans;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Question getNext() { 
		return next.get(); 
	}
    public void setNext(Question value) { 
    	next = Ref.create(value.getKey(), value);
    }
    
    com.googlecode.objectify.Key<Answer> getKey() {
        return com.googlecode.objectify.Key.create(Answer.class, id); 
    }
    
    public String getOutput () {
		String output = "<p class=\"ap\">" + this.answer + "</p>";
		if ( this.next != null ) {
			output += getNext().getOutput();
		} else {
			output += "<input type=\"button\" class=\"add-next\" value=\"Add follow up question >>>\" key=\"" + this.id + "\" />";
		}
		return output;
	}
}
