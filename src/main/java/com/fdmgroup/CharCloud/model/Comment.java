package com.fdmgroup.CharCloud.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comment {

	@Id
	@GeneratedValue
	private Integer id;
	private String text;
	
	@ManyToOne
	private User user;

	@ManyToOne
	private Collection collection;
	
	
	public Comment() {
		
	}


	public Comment(Integer id, String text, User user, Collection collection) {
		super();
		this.id = id;
		this.text = text;
		this.user = user;
		this.collection = collection;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Collection getCollection() {
		return collection;
	}


	public void setCollection(Collection collection) {
		this.collection = collection;
	}


	@Override
	public int hashCode() {
		return Objects.hash(collection, id, text, user);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		return Objects.equals(collection, other.collection) && Objects.equals(id, other.id)
				&& Objects.equals(text, other.text) && Objects.equals(user, other.user);
	}


	@Override
	public String toString() {
		return "Comment [id=" + id + ", text=" + text + ", user=" + user + ", collection=" + collection + ", getId()="
				+ getId() + ", getText()=" + getText() + ", getUser()=" + getUser() + ", getCollection()="
				+ getCollection() + ", hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()="
				+ super.toString() + "]";
	}
	
}


	