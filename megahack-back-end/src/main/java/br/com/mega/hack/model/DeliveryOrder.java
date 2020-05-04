package br.com.mega.hack.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class DeliveryOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private UserApp deliveryman;
	@ManyToOne
	private Order order;
	private Date dateDelivery;
	private Boolean delivered;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UserApp getDeliveryman() {
		return deliveryman;
	}
	public void setDeliveryman(UserApp deliveryman) {
		this.deliveryman = deliveryman;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	public Date getDateDelivery() {
		return dateDelivery;
	}
	public void setDateDelivery(Date dateDelivery) {
		this.dateDelivery = dateDelivery;
	}
	public Boolean getDelivered() {
		return delivered;
	}
	public void setDelivered(Boolean delivered) {
		this.delivered = delivered;
	}
}
