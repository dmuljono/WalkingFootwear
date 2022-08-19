package jor.empapp.payload.request;

import javax.validation.constraints.NotBlank;

public class OrderRequest {
	@NotBlank
	private Long productId;
	
	@NotBlank
	private Long customerId;

	@NotBlank
	private int quantity;
	

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	
}
