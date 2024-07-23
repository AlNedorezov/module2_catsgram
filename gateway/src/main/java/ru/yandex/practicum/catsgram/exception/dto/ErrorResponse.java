package ru.yandex.practicum.catsgram.exception.dto;

public class ErrorResponse {
	private final String error;

	public ErrorResponse(String error) {
		this.error = error;
	}

	public String getError() {
		return error;
	}
}