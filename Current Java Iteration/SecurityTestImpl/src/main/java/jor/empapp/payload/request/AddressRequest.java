package jor.empapp.payload.request;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

public class AddressRequest {
	
	@NotBlank
	private Long customId;
	
	@NotBlank
	private String addr;

	public Long getCustomId() {
		return customId;
	}

	public void setCustomId(Long customId) {
		this.customId = customId;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}



	
	
	

	
	
}
