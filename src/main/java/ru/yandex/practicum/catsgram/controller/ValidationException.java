package ru.yandex.practicum.catsgram.controller;

public class ValidationException extends RuntimeException {
	public ValidationException(String message) {
		super(message);
	}
}
