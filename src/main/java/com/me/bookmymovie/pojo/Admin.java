package com.me.bookmymovie.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.Table;

@Entity
@Table(appliesTo="Admin")
public class Admin {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ADMIN_ID")
    private long adminId;
	
	@OneToOne
    @JoinColumn(name="USER_ID")
    private User user;

	public long getAdminId() {
		return adminId;
	}

	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}