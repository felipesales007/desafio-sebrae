package br.com.mega.hack.model.dto.establishment;

import java.io.Serializable;
import java.util.Objects;

import br.com.mega.hack.model.Establishment;
import br.com.mega.hack.model.dto.address.AddressResponse;
import br.com.mega.hack.model.dto.user.UserResponse;

public class EstablishmentResponse implements Serializable {

	private static final long serialVersionUID = -2201184833411821251L;

	private Long id;
	private String imageURL;
	private String name;
	private AddressResponse address;
	private UserResponse user;

	public EstablishmentResponse(Establishment establishment) {
		this.id = establishment.getId();
		this.imageURL = establishment.getImageURL();
		this.name = establishment.getName();
		if(Objects.nonNull(establishment.getUser()))
			this.user = new UserResponse(establishment.getUser());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AddressResponse getAddress() {
		return address;
	}

	public void setAddress(AddressResponse address) {
		this.address = address;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public UserResponse getUser() {
		return user;
	}

	public void setUser(UserResponse user) {
		this.user = user;
	}

}
