package in.mvc.dto;

import java.util.List;

import lombok.Data;

@Data
public class RequestDto {

	private List<SearchRequestDto> searchRequestDto;
	
	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private String cardNumber;
	private String clientName;
	private String contact;
	private String country;
}
