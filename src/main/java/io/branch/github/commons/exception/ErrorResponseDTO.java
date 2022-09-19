package io.branch.github.commons.exception;

public class ErrorResponseDTO {
	private String message;
	private int code;

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(final int code) {
		this.code = code;
	}
}
