package jor.empapp.payload.request;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

public class FeedbackRequest {
	
	@NotBlank
	private Long customerId;
	
	@NotBlank
	private Long orderId;

	@NotBlank
	private int rating;
	
	private String comment;
	
	private boolean deliveryOnTime;


	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public boolean isDeliveryOnTime() {
		return deliveryOnTime;
	}

	public void setDeliveryOnTime(boolean deliveryOnTime) {
		this.deliveryOnTime = deliveryOnTime;
	}
	
	

	
	
}
