package br.com.mega.hack.model.dto;

public class OrderItemsRequest {

	private Long product;
	private Integer amount;
	
	public Long getProduct() {
		return product;
	}
	public void setProduct(Long product) {
		this.product = product;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	
}
