package br.com.mega.hack.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.mega.hack.model.enums.StatusOrder;

@Entity
@Table(name = "orders")
public class Order implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5385863217100996462L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date date;
	@ManyToOne
	private UserApp user;
	@ManyToOne
	private Establishment establishment;
	@Enumerated(EnumType.STRING)
	private StatusOrder status;
	private String addressDelivery;
	private String latitudeDelivery;
	private String longitudeDelivery;
	
	public Order() {
	}
	
	public Order(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public StatusOrder getStatus() {
		return status;
	}
	public void setStatus(StatusOrder status) {
		this.status = status;
	}
	public String getAddressDelivery() {
		return addressDelivery;
	}
	public void setAddressDelivery(String addressDelivery) {
		this.addressDelivery = addressDelivery;
	}
	public String getLatitudeDelivery() {
		return latitudeDelivery;
	}
	public void setLatitudeDelivery(String latitudeDelivery) {
		this.latitudeDelivery = latitudeDelivery;
	}
	public String getLongitudeDelivery() {
		return longitudeDelivery;
	}
	public void setLongitudeDelivery(String longitudeDelivery) {
		this.longitudeDelivery = longitudeDelivery;
	}
	public UserApp getUser() {
		return user;
	}
	public void setUser(UserApp user) {
		this.user = user;
	}
	public Establishment getEstablishment() {
		return establishment;
	}
	public void setEstablishment(Establishment establishment) {
		this.establishment = establishment;
	}
	
}
