package br.com.mega.hack.model.dto;

import br.com.mega.hack.model.Order;

public class DeliveryResponse {

	private Long order;
	private String from;
	private String to;
	private String receivingUser;
	
	public DeliveryResponse() {
	}
	
	public DeliveryResponse(Order order) {
		this.order = order.getId();
		this.from = order.getEstablishment().getCompleteAddress();
		this.to = order.getAddressDelivery();
		this.receivingUser = order.getUser().getName();
	}
	
	public Long getOrder() {
		return order;
	}
	public void setOrder(Long order) {
		this.order = order;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getReceivingUser() {
		return receivingUser;
	}
	public void setReceivingUser(String receivingUser) {
		this.receivingUser = receivingUser;
	}
	
	
	
}
