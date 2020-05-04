package br.com.mega.hack.model.enums;

public enum Role {
	USUARIO("Usuario", "ROLE_USER"),
	VENDEDOR("Vendedor", "ROLE_VENDEDOR"), 
	ENTREGADOR("Entreagdor", "ROLE_ENTREGADOR");

	private String label;
	private String role;

	private Role(String label, String role) {
		this.label = label;
		this.role = role;
	}

	public String getLabel() {
		return label;
	}

	public String getRole() {
		return role;
	}

	public static String toLabel(String enumString) {
		if (enumString == null) {
			return null;
		}
		for (Role ep : Role.values()) {
			if (enumString.equals(ep.toString())) {
				return ep.getLabel();
			}
		}
		return null;
	}

	public static Role toEnum(String label) {
		System.out.println("toUnum " + label);
		if (label == null) {
			return null;
		}
		for (Role ep : Role.values()) {
			if (label.equals(ep.getLabel())) {
				return ep;
			}
		}
		return null;
	}

}
