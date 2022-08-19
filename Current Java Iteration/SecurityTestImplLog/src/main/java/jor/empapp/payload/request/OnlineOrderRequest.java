package jor.empapp.payload.request;

import javax.validation.constraints.NotBlank;

import jor.empapp.models.Product;

public class OnlineOrderRequest {
	
	@NotBlank
	private Long customId;
	
	@NotBlank
	private String addr;
	
	@NotBlank
	private Long[] productIds;
	
	@NotBlank
	private Long[] quantitys;

	public Long getCustomId() {
		return customId;
	}

	public void setCustomId(Long customId) {
		this.customId = customId;
	}

	public Long[] getProductIds() {
		return productIds;
	}

	public void setProductIds(Long[] productIds) {
		this.productIds = productIds;
	}

	public Long[] getQuantitys() {
		return quantitys;
	}

	public void setQuantitys(Long[] quantity) {
		this.quantitys = quantity;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	
	
	

	
	
}
