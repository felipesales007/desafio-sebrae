package br.com.mega.hack.model.dto;

import br.com.mega.hack.model.Order;

public class DeliveryRouteResponse {

	private Long idOrder;
	private String toLongitude;
	private String toLatitude;
	private String fromLongitude;
	private String fromLatitude;
	private String toAddress;
	private String fromAddress;
	
	public DeliveryRouteResponse() {
	}
	
	public DeliveryRouteResponse(Order order) {
		this.idOrder = order.getId();
		this.toAddress = order.getAddressDelivery();
		this.toLatitude = order.getLatitudeDelivery();
		this.toLongitude = order.getLongitudeDelivery();
		this.fromAddress = order.getEstablishment().getCompleteAddress();
		this.fromLongitude = order.getEstablishment().getLongitude();
		this.fromLatitude = order.getEstablishment().getLatitude();
	}
	
	public Long getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(Long idOrder) {
		this.idOrder = idOrder;
	}
	public String getToLongitude() {
		return toLongitude;
	}
	public void setToLongitude(String toLongitude) {
		this.toLongitude = toLongitude;
	}
	public String getToLatitude() {
		return toLatitude;
	}
	public void setToLatitude(String toLatitude) {
		this.toLatitude = toLatitude;
	}
	public String getFromLongitude() {
		return fromLongitude;
	}
	public void setFromLongitude(String fromLongitude) {
		this.fromLongitude = fromLongitude;
	}
	public String getFromLatitude() {
		return fromLatitude;
	}
	public void setFromLatitude(String fromLatitude) {
		this.fromLatitude = fromLatitude;
	}
	public String getToAddress() {
		return toAddress;
	}
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	public String getFromAddress() {
		return fromAddress;
	}
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	
	
}
