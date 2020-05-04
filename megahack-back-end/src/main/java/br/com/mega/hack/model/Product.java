package br.com.mega.hack.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.mega.hack.model.dto.ProductRequest;

@Entity
public class Product implements Serializable {

	private static final long serialVersionUID = 626238731008106050L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String type;
	@Column(name = "image_url")
	private String imageURL;
	@ManyToOne
	private Establishment establishment;
	private Float price;
	private Boolean active;
	private String description;

	public Product(ProductRequest request) {
		this.name = request.getName();
		this.type = request.getType();
		this.price = request.getPrice();
		this.active = Boolean.TRUE;
		this.description = request.getDescription();
		this.establishment = new Establishment(request.getEstablishment());
	}

	public Product(Long id) {
		this.id = id;
	}
	
	public Product() {
	}

	public Product(Long id, Boolean active) {
		this.id = id;
		this.active = active;
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

	public Establishment getEstablishment() {
		return establishment;
	}

	public void setEstablishment(Establishment establishment) {
		this.establishment = establishment;
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
