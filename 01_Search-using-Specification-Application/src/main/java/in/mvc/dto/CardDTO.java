package in.mvc.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CardDTO {
	private int id;
	private String username;
	private String cardNumber;
	private String cardArray;
	private String clientName;
	private String firstName;
	private String lastName;
	private Date doB;
	private String gender;
	private String contact;
	private String email;
//	private String password;
	private String address;
	private String state;
	private String pinCode;
	private String country;
}