package io.home.hibernateapp.dto;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/*	@Entity (name="USER_DETAILS") changes the entity name as well as Table name, 
 *	whereas, @Table (name="USER_DETAILS") changes only the Table name.
 *  Entity name (i.e. UserDetails) will remain the same
 */

@Entity
@Table (name="USER_DETAILS")
public class UserDetails {

	/* You can place field level annotations on top of getters also 
	 * @Basic creates data types in SQL which map to data types in JAVA
	 * @Transient fields won't be persisted by Hibernate
	 * @Temporal defined date type.
	 * 
	 * @Lob means large object
	 * CLOB - Character type 
	 * BLOB - Byte Stream 
	 */

	@Id
	@Column(name="USER_ID")
	private int userId;
	
	@Column(name="USER_NAME")
	private String userName;
	
	@Temporal(TemporalType.DATE)
	private Date joiningDate;
	
	@Basic
	@Transient
	private String address;
	
	@Lob
	private String description;
	
	
	public UserDetails(int userId, String userName, 
			Date joiningDate, String address, String description) {
		super();
		
		setUserId(userId);
		setUserName(userName);
		
		setJoiningDate(joiningDate);
		setAddress(address);
		setDescription(description);
	}
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName + " from getter";
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
