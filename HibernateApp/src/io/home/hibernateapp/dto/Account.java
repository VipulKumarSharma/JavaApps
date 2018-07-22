package io.home.hibernateapp.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name="ACCOUNT")
public class Account {

	@Id 
	@GeneratedValue (strategy=GenerationType.AUTO)
	@Column (name="ACCOUNT_NO")
	private long accountNo;
	
	@Column (name="ACCOUNT_NAME")
	private String accountName;
	
	@ManyToOne
	@JoinColumn(name="EMPLOYEE_ID")
	private Employee employee;

	/****************************************************************************/
	public Account() {}

	public Account(String accountName) {
		super();
		setAccountName(accountName);
	}

	/****************************************************************************/
	public long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	/****************************************************************************/
}
