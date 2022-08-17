package jor.empapp.payload.request;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

public class FeedbackRequest {
	@NotBlank
	private Long productId;
	
	@NotBlank
	private Long customerId;
	
	@NotBlank
	private Long orderId;

	@NotBlank
	private int rating;
	
	private String comment;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

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
	

	
	
}
