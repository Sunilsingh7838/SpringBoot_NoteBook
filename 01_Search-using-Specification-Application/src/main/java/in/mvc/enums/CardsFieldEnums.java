package in.mvc.enums;

public enum CardsFieldEnums {
	FIRST_NAME("firstName"),USERNAME("username"),CARD_NUMBER("cardNumber"),CLIENT_NAME("clientName"),EMAIL("email"),
	COUNTRY("country");

	private String value;

	CardsFieldEnums(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
