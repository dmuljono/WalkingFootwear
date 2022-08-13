package jor.empapp.payload.request;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

public class ProductRequest {
	@NotBlank
	private String sku;
	
	@NotBlank
    private Long categoryId;
	
    @NotBlank
    private String name;
	
    @NotBlank
    private String description;

    @NotBlank
    private BigDecimal unitPrice;

    @NotBlank
    private String imageUrl;

    @NotBlank
    private boolean available;

    @NotBlank
    private int unitsInStock;

	

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public int getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(int unitsInStock) {
		this.unitsInStock = unitsInStock;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	

	
}
