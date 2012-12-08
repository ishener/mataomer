package com.hatzilim.mataomer;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

@Entity
public class Answer {
	@Id Long id;
	@Index String answer;
	@Index @Load Ref<Question> next;
	
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
		if (next == null) return null;
		return next.get(); 
	}
    public void setNext(Question value) { 
    	if (value == null)
    		next = null;
    	else
    		next = Ref.create(value.getKey(), value);
    }
    
    com.googlecode.objectify.Key<Answer> getKey() {
        return com.googlecode.objectify.Key.create(Answer.class, id); 
    }
    
    public String getOutput () {
    	StringBuilder strBuilder= new StringBuilder();
    	strBuilder.append("<div class=\"answer-div\"><p class=\"ap\">").append( this.answer ).append("</p>");
		if ( this.next != null ) {
			strBuilder.append( getNext().getOutput() );
		} else {
			strBuilder.append( "<input type=\"button\" class=\"add-next\" value=\"Add follow up question >>>\" key=\"" )
					  .append( this.id )
					  .append( "\" />" );
		}
		strBuilder.append("</div>");
		return strBuilder.toString();
	}
}
