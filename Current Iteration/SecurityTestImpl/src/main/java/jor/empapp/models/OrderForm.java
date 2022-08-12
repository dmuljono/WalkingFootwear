package jor.empapp.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="Orders")
public class OrderForm {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
	private Long orderId;
	
	@ManyToOne
	@JoinColumn(name="product", referencedColumnName="product_id", nullable=false)
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="customer", referencedColumnName="customer_id", nullable=true)
	@JsonManagedReference
	private Customer customer;
	
	@Column(name = "date_created")
    @CreationTimestamp
	private Date purchaseDate;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "totalAmount")
	private double totalAmount;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void findTotalAmount() {
		totalAmount = this.product.getUnitPrice().doubleValue()*this.quantity;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "OrderForm [orderId=" + orderId + ", product=" + product + ", customer=" + customer + ", purchaseDate="
				+ purchaseDate + ", quantity=" + quantity + ", totalAmount=" + totalAmount + "]";
	}
	
	
	
	
}
