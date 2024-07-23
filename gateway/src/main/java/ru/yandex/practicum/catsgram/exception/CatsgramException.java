package ru.yandex.practicum.catsgram.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CatsgramException extends RuntimeException {
    public CatsgramException(String message) {
        super(message);
    }

    public CatsgramException(String message, Throwable cause) {
        super(message, cause);
    }
}