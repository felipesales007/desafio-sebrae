package br.com.mega.hack.model.dto.establishment;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import br.com.mega.hack.model.dto.address.AddressRequest;

public class EstablishmentRequest implements Serializable {

	private static final long serialVersionUID = -2201184833411821251L;

	@NotNull
	private AddressRequest address;

	@NotNull
	private String name;

	public EstablishmentRequest() {

	}

	public AddressRequest getAddress() {
		return address;
	}

	public void setAddress(AddressRequest address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
