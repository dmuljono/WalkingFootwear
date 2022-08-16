package jor.empapp.payload.request;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

public class WalkInOrderRequest {
	@NotBlank
	private Long[] productIds;

	@NotBlank
	private Long[] quantity;
	
	private String phoneNumber;
	
	private String email;

	public Long[] getProductIds() {
		return productIds;
	}

	public void setProductIds(Long[] productIds) {
		this.productIds = productIds;
	}


	public Long[] getQuantity() {
		return quantity;
	}

	public void setQuantity(Long[] quantity) {
		this.quantity = quantity;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	
	
}
