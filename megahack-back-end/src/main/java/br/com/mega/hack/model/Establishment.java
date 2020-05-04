package br.com.mega.hack.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.mega.hack.model.dto.establishment.EstablishmentRequest;

@Entity
public class Establishment implements Serializable {

	private static final long serialVersionUID = -2201184833411821251L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "image_url")
	private String imageURL;
	@ManyToOne(fetch = FetchType.EAGER)
	private UserApp user;
	@ManyToOne
	private Address address;
	private String latitude;
	private String longitude;
	private String name;

	public Establishment() {

	}

	public Establishment(Long id) {
		this.id = id;
	}

	public Establishment(EstablishmentRequest establishmentRequest, Address address, Long user) {
		this.name = establishmentRequest.getName();
		this.address = address;
		this.user = new UserApp(user);
	}

	public Establishment getThis() {
		return this;
	}

	public String userUUID() {
		return this.user.getUuid();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public UserApp getUser() {
		return user;
	}

	public void setUser(UserApp user) {
		this.user = user;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getCompleteAddress() {
		return address.getStreet() + ", " + address.getNumber() + ". Cep: " + address.getZipCode();
	}

}
