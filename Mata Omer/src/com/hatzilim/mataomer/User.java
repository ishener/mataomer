package com.hatzilim.mataomer;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class User {
	@Id String id;
	@Index String email;
	@Index Date creation;
	Date lastSeen;
	
	public User () {
		
	}
	public User (String email, String id) {
		this.id = id;
		this.email = email;
		this.creation = new Date();
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCreation() {
		return creation;
	}
	public void setCreation(Date creation) {
		this.creation = creation;
	}
	public Date getLastSeen() {
		return lastSeen;
	}
	public void setLastSeen(Date lastSeen) {
		this.lastSeen = lastSeen;
	}
	com.googlecode.objectify.Key<User> getKey() {
        return com.googlecode.objectify.Key.create(User.class, id); 
    }
}
