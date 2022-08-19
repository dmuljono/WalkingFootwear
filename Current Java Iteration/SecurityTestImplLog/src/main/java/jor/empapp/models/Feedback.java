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
@Table(name="feedback")
public class Feedback {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedbackId")
	private Long feedbackId;
	
	@ManyToOne
	@JoinColumn(name="product", referencedColumnName="product_id", nullable=false)
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="customer", referencedColumnName="customer_id", nullable=true)
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="orders", referencedColumnName="orderId", nullable=true)
	private OrderForm order;
	
	@Column(name = "deliveryOnTime")
	private boolean deliveryOnTime;
	
	@Column(name = "rating")
	private int rating;
	
	@Column(name = "feedback")
	private String comment;
	
	@Column(name = "date_created")
    @CreationTimestamp
	private Date dateCreated;

	public Long getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(Long feedbackId) {
		this.feedbackId = feedbackId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public boolean isDeliveryOnTime() {
		return deliveryOnTime;
	}

	public void setDeliveryOnTime(boolean deliveryOnTime) {
		this.deliveryOnTime = deliveryOnTime;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}


	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public OrderForm getOrder() {
		return order;
	}

	public void setOrder(OrderForm order) {
		this.order = order;
	}
	
	

	
	
	
	
}
