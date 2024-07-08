package ru.yandex.practicum.catsgram.controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.catsgram.post.dto.PostDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PostControllerTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @AllArgsConstructor
    static class ExpectedViolation {
        String propertyPath;
        String message;
    }

    @Test
    void validateDescriptionTooShortFail() {
        final PostDto post = new PostDto(
                "user@mail.ru",
                "test",
                "https://online.ssidigital.in/p/apachemaven"
        );
        List<ConstraintViolation<PostDto>> violations = new ArrayList<>(validator.validate(post));
        ExpectedViolation expectedViolation = new ExpectedViolation(
                "description", "size must be between 5 and 2147483647");
        assertEquals(1, violations.size());
        assertEquals(
                expectedViolation.propertyPath,
                violations.get(0).getPropertyPath().toString()
        );
        assertEquals(
                expectedViolation.message,
                violations.get(0).getMessage()
        );
    }
}