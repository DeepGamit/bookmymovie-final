package com.me.bookmymovie.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;


import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.MetaValue;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Table;

@SuppressWarnings("deprecation")
@Entity
@Table(appliesTo="User")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_ID", unique = true, nullable = false)
	private long userID;
	
	@NotEmpty(message="Please enter your user name!")
	@Size(min = 3, message="Your username should have aleast 3 characters")
	@Column(name="USER_NAME")
	private String userName;

	@Email(message="Please enter a valid email address!")
	@NotEmpty(message="Please enter your email address!")
	@NaturalId(mutable=false)
	@Column(name="USER_EMAIL", unique = true, nullable = false)
	private String userEmail;
	
	@NotEmpty(message="Please enter your password")
	@Column(name="USER_PWD", nullable = false)
	private String userPwd;
	
	@Column(name= "REG_DATE")
	@Temporal(TemporalType.DATE)
	private Date regDate;
	
	@Column(name= "LAST_LOG")
	@Temporal(TemporalType.DATE)
	private Date lastLog;
	
	@Column(name = "USER_TYPE")
	private String userType;
	
	@Column(name = "USER_STATUS")
	private String userStatus;
	
	public String getUserStatus() {
		return userStatus;
	}
	
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Date getRegDate() {
		return regDate;
	}
	
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	public Date getLastLog() {
		return lastLog;
	}
	
	public void setLastLog(Date lastLog) {
		this.lastLog = lastLog;
	}
	
	public long getUserID() {
		return userID;
	}
	
	public void setUserID(long userID) {
		this.userID = userID;
	}
	
	public String getUserType() {
		return userType;
	}
	
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public String getUserEmail() {
		return userEmail;
	}
	
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public String getUserPwd() {
		return userPwd;
	}
	
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
}
