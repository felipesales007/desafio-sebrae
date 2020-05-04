package br.com.mega.hack.model;

import static java.util.Objects.nonNull;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.mega.hack.model.dto.address.AddressRequest;

@Entity
public class Address implements Serializable {

	private static final long serialVersionUID = 612870000864307517L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String parents;
	private String state;
	private String city;
	private String neighborhood;
	private String street;
	private Integer number;
	private String zipCode;
	private String latitude;
	private String longitude;

	public Address() {

	}

	public Address(AddressRequest address) {
		this.parents = address.getParents();
		this.state = address.getState();
		this.city = address.getCity();
		this.neighborhood = address.getNeighborhood();
		this.street = address.getStreet();
		this.zipCode = address.getZipCode();
		this.latitude = address.getLatitude();
		this.longitude = address.getLongitude();
		if (nonNull(address.getNumber())) {
			this.number = address.getNumber();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getParents() {
		return parents;
	}

	public void setParents(String parents) {
		this.parents = parents;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
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

}
