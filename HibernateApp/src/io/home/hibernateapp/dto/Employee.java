package io.home.hibernateapp.dto;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name="EMPLOYEE")
public class Employee {

	@Id 
	@GeneratedValue (strategy=GenerationType.AUTO)
	@Column (name="EMPLOYEE_ID")
	private int employeeId;
	
	@Column (name="EMPLOYEE_NAME")
	private String employeeName;
	
	@OneToOne
	@JoinColumn(name="VEHICLE_ID")
	private Vehicle vehicle;
	
	/* If @JoinTable is mentioned in @OneToMany, 
	 * it creates a separate mapping table containing both tables PK's as FK's 
	 * but, if 
	 * @OneToMany(mappedBy="employee"), 
	 * where "employee" is the fieldName in Account Entity 
	 * then it won't create a separate table.
	 * A column of One's side is added to the many side.
	 */
	
	/*
	@OneToMany
	@JoinTable(	name		= "EMPLOYEE_ACCOUNTS", 
		joinColumns			= @JoinColumn(name="EMPLOYEE_ID"),
		inverseJoinColumns	= @JoinColumn(name="ACCOUNT_NO"))
	*/
	@OneToMany(mappedBy="employee")
	private Collection<Account> accounts;
	
	/****************************************************************************/
	public Employee() {}
	
	public Employee(String employeeName, Vehicle vehicle) {
		super();
		setEmployeeName(employeeName);
		setVehicle(vehicle);
		accounts = new ArrayList<Account>();
	}
	
	/****************************************************************************/
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	public Collection<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(Collection<Account> accounts) {
		this.accounts = accounts;
	}
	
	/****************************************************************************/
}
