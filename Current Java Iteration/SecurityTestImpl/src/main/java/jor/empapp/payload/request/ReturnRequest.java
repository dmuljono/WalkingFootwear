package jor.empapp.payload.request;

import javax.validation.constraints.NotBlank;

public class ReturnRequest {
	@NotBlank
	private Long orderId;
	
	@NotBlank
	private Long customerId;

	@NotBlank
	private String reasonForReturn;


	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getReasonForReturn() {
		return reasonForReturn;
	}

	public void setReasonForReturn(String reasonForReturn) {
		this.reasonForReturn = reasonForReturn;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	

	
}
