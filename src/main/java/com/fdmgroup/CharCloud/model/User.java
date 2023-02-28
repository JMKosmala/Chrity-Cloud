package com.fdmgroup.CharCloud.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table
public class User {

		@Id
		@GeneratedValue (strategy = GenerationType.IDENTITY)
		private Integer id;

		@ManyToOne(cascade = CascadeType.ALL)
		private Role role;
		
		private String name;
		private String surname;
		private String email;
		private String username;
		private String password;
	    @Transient
	    private String confirmPassword;
		private Integer donatedTotal=0;
		private Integer lastDonation=0;
		@OneToMany(cascade = CascadeType.ALL)
		@JoinTable (name="UsersDonationsList")
		private List <Collection>myCollections;
		private Integer charPoints=0;
		private String img="https://i2.wp.com/www.cycat.io/wp-content/uploads/2018/10/Default-user-picture.jpg";
				
		
		public String getImg() {
			return img;
		}

		public void setImg(String img) {
			this.img = img;
		}

		//constructors 
		public User() {
			super();
		}
		
		public User(String username) {
			this.username = username;
		}
		
		public User(Role role, String username, String name, String surname, String password,  String email) {
			super();
			this.role = role;
			this.name = name;
			this.surname = surname;
			this.username = username;
			this.password = password;
			this.email = email;
		}
		
		//getters and setters

		
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Role getRole() {
			return role;
		}

		public void setRole(Role role) {
			this.role = role;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSurname() {
			return surname;
		}

		public void setSurname(String surname) {
			this.surname = surname;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getConfirmPassword() {
			return confirmPassword;
		}

		public void setConfirmPassword(String confirmPassword) {
			this.confirmPassword = confirmPassword;
		}

		public Integer getDonatedTotal() {
			return donatedTotal;
		}

		public void setDonatedTotal(Integer donatedTotal) {
			this.donatedTotal = donatedTotal;
		}

		public Integer getLastDonation() {
			return lastDonation;
		}

		public void setLastDonation(Integer lastDonation) {
			this.lastDonation = lastDonation;
		}

		public List<Collection> getMyCollections() {
			return myCollections;
		}

		public void setMyCollections(List<Collection> myCollections) {
			this.myCollections = myCollections;
		}

		public Integer getCharPoints() {
			return charPoints;
		}

		public void setCharPoints(Integer charPoints) {
				
			this.charPoints = charPoints;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((password == null) ? 0 : password.hashCode());
			result = prime * result + ((role == null) ? 0 : role.hashCode());
			result = prime * result + ((username == null) ? 0 : username.hashCode());
			return result;
		}

		@Override
		public String toString() {
			return "User [id=" + id + ", role=" + role + ", name=" + name + ", surname=" + surname + ", email=" + email
					+ ", username=" + username + ", donatiedTotal=" + donatedTotal + "]";
		}

		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			User other = (User) obj;
			if (password == null) {
				if (other.password != null)
					return false;
			} else if (!password.equals(other.password))
				return false;
			if (role == null) {
				if (other.role != null)
					return false;
			} else if (!role.equals(other.role))
				return false;
			if (username == null) {
				if (other.username != null)
					return false;
			} else if (!username.equals(other.username))
				return false;
			return true;
		}
		
		
		
}
