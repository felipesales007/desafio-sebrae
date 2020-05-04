package br.com.mega.hack.model.dto;

import java.io.Serializable;

import br.com.mega.hack.model.OrderItems;

public class OrderItemsResponse implements Serializable {
	private static final long serialVersionUID = -6715076865290900015L;

	private Long id;
	private ProductResponse product;
	private Integer amount;

	public OrderItemsResponse() {

	}

	public OrderItemsResponse(OrderItems item) {
		super();
		this.product = new ProductResponse(item.getProduct());
		this.amount = item.getAmount();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProductResponse getProduct() {
		return product;
	}

	public void setProduct(ProductResponse product) {
		this.product = product;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}
