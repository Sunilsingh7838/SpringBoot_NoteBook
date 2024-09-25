package in.mvc.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "CARD_DETAILS_TABLE")
public class CardsTable {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@Column(name = "USER_NAME")
	private String username;
	
	@Column(name = "CARD_NUMBER")
	private String cardNumber;
	
	@Column(name = "CARD_ARRAY")
	private String cardArray;
	
	@Column(name = "CLIENT_NAME")
	private String clientName;
	
	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Temporal(TemporalType.DATE)
	@Column(name = "DOB")
	private Date doB;

	@Column(name = "GENDER")
	private String gender;

	@Column(name = "CONTACT")
	private String contact;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "STATE")
	private String state;

	@Column(name = "PINCODE")
	private String pinCode;

	@Column(name = "COUNTRY")
	private String country;

}
