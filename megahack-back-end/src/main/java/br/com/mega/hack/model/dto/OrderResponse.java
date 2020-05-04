package br.com.mega.hack.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.mega.hack.model.Establishment;
import br.com.mega.hack.model.Order;
import br.com.mega.hack.model.dto.establishment.EstablishmentResponse;

public class OrderResponse {
	private Long id;
	private String addressDelivery;
	private String latitudeDelivery;
	private String longitudeDelivery;
	private String status;
	private Date date;
	private EstablishmentResponse establishment;

	private List<OrderItemsResponse> orderItems = new ArrayList<OrderItemsResponse>();

	public OrderResponse() {

	}

	public OrderResponse(Order order) {
		this.id = order.getId();
		this.addressDelivery = order.getAddressDelivery();
		this.status = order.getStatus().name();
		this.date = order.getDate();
		this.latitudeDelivery = order.getLatitudeDelivery();
		this.longitudeDelivery = order.getLongitudeDelivery();
		this.establishment = new EstablishmentResponse(order.getEstablishment());
	}

	public EstablishmentResponse getEstablishment() {
		return establishment;
	}

	public void setEstablishment(EstablishmentResponse establishment) {
		this.establishment = establishment;
	}

	public List<OrderItemsResponse> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemsResponse> orderItems) {
		this.orderItems = orderItems;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
