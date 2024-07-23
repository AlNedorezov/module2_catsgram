package ru.yandex.practicum.catsgram.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ru.yandex.practicum.catsgram.exception.dto.ErrorResponse;


@RestControllerAdvice
public class ErrorHandler {
	private static final Logger log = LoggerFactory.getLogger(ErrorHandler.class);

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
		log.info("400 {}", e.getMessage(), e);
		return new ErrorResponse(e.getMessage());
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse handleThrowable(final Throwable e) {
		log.info("500 {}", e.getMessage(), e);
		return new ErrorResponse(e.getMessage());
	}
}