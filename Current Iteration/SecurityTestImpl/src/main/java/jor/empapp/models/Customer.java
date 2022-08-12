package jor.empapp.models;

import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Table(name="Customer", uniqueConstraints = {@UniqueConstraint(columnNames = "email") })
public class Customer{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="customer_id")
	Long customerId;
	@NotBlank
	@Size(max = 20)
	String firstName;
	@NotBlank
	@Size(max = 20)
	String lastName;
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;
	@Column(name="phoneNumber")
	String phoneNumber;
	@Column(name="address")
	String address;
	@NotBlank
	@Size(max = 120)
	private String password;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "customer_roles", joinColumns = @JoinColumn(name = "customer_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<CustomerRole> roles;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	@JsonBackReference
	private Set<OrderForm> orders;
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getCustomerID() {
		return customerId;
	}
	public void setCustomerID(Long customerID) {
		this.customerId = customerID;
	}
	public Set<CustomerRole> getRoles() {
		return roles;
	}
	public void setRoles(Set<CustomerRole> roles) {
		this.roles = roles;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Set<OrderForm> getOrders() {
		return orders;
	}
	public void setOrders(Set<OrderForm> orders) {
		this.orders = orders;
	}

	
	
	
	
}
