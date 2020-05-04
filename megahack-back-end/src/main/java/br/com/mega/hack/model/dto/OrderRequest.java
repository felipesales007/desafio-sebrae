package br.com.mega.hack.model.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

import br.com.mega.hack.model.Establishment;
import br.com.mega.hack.model.Order;
import br.com.mega.hack.model.UserApp;
import br.com.mega.hack.model.enums.StatusOrder;

public class OrderRequest {
	
	private long establishment;
	private String addressDelivery;
	private String latitudeDelivery;
	private String longitudeDelivery;
	private Set<OrderItemsRequest> orderItems;
	
	public long getEstablishment() {
		return establishment;
	}
	public void setEstablishment(long establishment) {
		this.establishment = establishment;
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
	
	
	public Set<OrderItemsRequest> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(Set<OrderItemsRequest> orderItems) {
		this.orderItems = orderItems;
	}
	public Order getNewOrder() {
		Order order = new Order();
		order.setDate(new Date());
		order.setEstablishment(new Establishment(this.establishment));
		order.setStatus(StatusOrder.PRONTO);
		order.setAddressDelivery(this.addressDelivery);
		order.setLatitudeDelivery(this.latitudeDelivery);
		order.setLongitudeDelivery(this.longitudeDelivery);
		return order;
	}
	
}
