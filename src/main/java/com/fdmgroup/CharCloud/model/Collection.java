package com.fdmgroup.CharCloud.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Collection {

	
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String title;
	Integer amount=0;
	Integer collected=0;
	@ManyToOne
	private User owner;
	private String description;
	private String img;
	
	@OneToMany
	private List<Comment> comments =new ArrayList<>();



	
	public Collection(String title, Integer amount, User owner, String description, String img) {

		super();
		this.title = title;
		this.amount = amount;
		this.owner = owner;
		this.description = description;
		this.img = img;
	}

	public Collection() {
		super();
	}
	
	 public void addComment(Comment comment) {
	        comments.add(comment);
	        comment.setCollection(this);
	    }

	    public void removeComment(Comment comment) {
	        comments.remove(comment);
	        comment.setCollection(null);
	    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public User getDonor() {
		return owner;
	}

	public void setDonor(User donor) {
		this.owner = donor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getCollected() {
		return collected;
	}

	public void setCollected(Integer collected) {
		this.collected = collected;
	}
	@Override
	public int hashCode() {
		return Objects.hash(amount, collected, description, id, img, owner, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Collection other = (Collection) obj;
		return Objects.equals(amount, other.amount) && Objects.equals(collected, other.collected)
				&& Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(img, other.img) && Objects.equals(owner, other.owner)
				&& Objects.equals(title, other.title);
	}
}