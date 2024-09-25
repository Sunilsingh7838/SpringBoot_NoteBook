package in.mvc.dto;

import lombok.Data;

@Data
public class ResponseDto {
	private Object data;

	private String message;

	private int errorCode;

	private String status;

	private int statusCode;

	private boolean success;
}
