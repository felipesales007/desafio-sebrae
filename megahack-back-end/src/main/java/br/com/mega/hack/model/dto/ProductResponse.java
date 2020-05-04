package br.com.mega.hack.model.dto;

import br.com.mega.hack.model.Product;

public class ProductResponse {

	private Long id;
	private String name;
	private String type;
	private String imageURL;
	private Float price;
	private Boolean active;
	private String description;
	
	public ProductResponse(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.type = product.getType();
		this.imageURL = product.getImageURL();
		this.price = product.getPrice();
		this.active = product.getActive();
		this.description = product.getDescription();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
}