package io.home.hibernateapp.dto;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/* @Embeddable is used when we want to use the object inside other Entity */

@Embeddable
public class Address {
	
	@Column(name="STREET_NAME")
	private String street;
	@Column(name="CITY_NAME")
	private String city;
	@Column(name="STATE")
	private String state;
	@Column(name="PINCODE")
	private String pincode;
	
	/****************************************************************************/
	public Address () {}
	
	public Address(String street, String city, String state, String pincode) {
		super();
		
		setStreet(street);
		setCity(city);
		setState(state);
		setPincode(pincode);
	}
	
	/****************************************************************************/
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
}
