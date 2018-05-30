package io.home.hibernateapp.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

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
	 * 
	 * @Embedded field is used with @Embeddable Object,
	 * which is inserted into DB with container Entity Object.
	 * Even Primary key can be an Embedded Object, by using @EmbeddedId
	 * @EmbeddedId does not work with @Id and @GeneratedValue.
	 * 
	 * @AttributeOverrides overrides @Embeddable Object's fields
	 * @AttributeOverride overrides @Embeddable Object's field properties 
	 * (i.e. DB column name)
	 * 
	 * @ElementCollection is used to embed Collection Object inside @Embeddable Object
	 * Separate table is created to store collection objects,
	 * First n(i.e. size of collection) no. of rows will be created with collection data,
	 * then table's records will be updated with Main table's primary key value.
	 * 
	 * @JoinTable used to alter name of Auto generated Join table and It's FOREIGN_KEY column name
	 * name="USER_ADDRESS" - alters table name	
	 * joinColumns=@JoinColumn(name="User_ID")) - alters FOREIGN_KEY column name
	 * 
	 * To create PK in join table use collection that supports index (i.e. ArrayList [0],[1],[2])
	 * Set does not support indexes.
	 * @CollectionId(columns = { @Column }, generator = "", type = @Type)
	 * It is used to generate PK in join tables.
	 * It is NOT a JPA annotation, it's a hibernate feature/annotation
	 * @Column 		- defined which column you want to define as PK 
	 * generator 	- How is the PK to be generated
	 * @Type		- Type of the PK
	 * 
	 * @GenericGenerator is hibernate generator
	 * hilo is common generator used by hibernate
	 * 
	 * @ElementCollection(fetch=FetchType.EAGER) - for Eager fetching of collection object's data from DB 
	 */

	
	@Id 
	@GeneratedValue (strategy=GenerationType.AUTO)
	@Column (name="USER_ID")
	//@EmbeddedId
	private int userId;
	
	@Column(name="USER_NAME")
	private String userName;
	
	@Temporal(TemporalType.DATE)
	private Date joiningDate;
	
	@Basic
	@Transient
	private String nickname;
	
	@Lob
	private String description;
	
	@Embedded
	private Address homeAddress;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="street", 	column=@Column(name="OFFICE_STREET_NAME")),
		@AttributeOverride(name="city", 	column=@Column(name="OFFICE_CITY_NAME")),
		@AttributeOverride(name="state", 	column=@Column(name="OFFICE_STATE")),
		@AttributeOverride(name="pincode", 	column=@Column(name="OFFICE_PINCODE")),
	})
	private Address officeAddress;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@JoinTable(
		name="USER_ADDRESS",
		joinColumns=@JoinColumn(name="USER_ID")
	)
	@GenericGenerator(
		name = "hilo-gen", 
		strategy = "hilo"
	)
	@CollectionId(
		columns = { @Column(name="ADDRESS_ID") }, 
		generator = "hilo-gen", 
		type = @Type(type = "long")
	)
	private Collection<Address> addressesOverTheYears = new ArrayList<Address>();
	
	
	
	/****************************************************************************/
	public UserDetails() {}
	
	public UserDetails(int userId, String userName, 
			Date joiningDate, String nickname, String description) {
		super();
		
		//setUserId(userId);
		setUserName(userName);
		
		setJoiningDate(joiningDate);
		setNickname(nickname);
		setDescription(description);
	}
	
	
	
	
	/****************************************************************************/
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName + " using getter";
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Address getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}
	public Address getOfficeAddress() {
		return officeAddress;
	}
	public void setOfficeAddress(Address officeAddress) {
		this.officeAddress = officeAddress;
	}
	public Collection<Address> getAddressesOverTheYears() {
		return addressesOverTheYears;
	}
	public void setAddressesOverTheYears(Collection<Address> addressesOverTheYears) {
		this.addressesOverTheYears = addressesOverTheYears;
	}
}
