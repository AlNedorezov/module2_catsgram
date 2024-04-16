package ru.yandex.practicum.catsgram.controller;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.Update;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PostControllerTest {

    static PostController postController = new PostController();
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @AllArgsConstructor
    static class ExpectedViolation {
        String propertyPath;
        String message;
    }

    @Test
    void validatePostOk() {
        final Post validPost = new Post(
                "name@mail.ru",
                "test post",
                "https://online.ssidigital.in/p/apachemaven"
        );
        postController.validate(validPost);
    }

    @Test
    void validatePostFail() {
        final Post post = new Post(
                null,
                "test post",
                "https://online.ssidigital.in/p/apachemaven"
        );
        Exception exception = assertThrows(
                ValidationException.class,
                () -> postController.validate(post)
        );
        assertEquals(
                "Post author invalid",
                exception.getMessage()
        );
    }

    @Test
    void validateDescriptionTooShortFail() {
        final Post post = new Post(
                "user@mail.ru",
                "test",
                "https://online.ssidigital.in/p/apachemaven"
        );
        List<ConstraintViolation<Post>> violations = new ArrayList<>(validator.validate(post));
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

    @Test
    void validateIdNotSetForUpdateFail() {
        final Post post = new Post(
                "user@mail.ru",
                "test post",
                "https://online.ssidigital.in/p/apachemaven"
        );
        List<ConstraintViolation<Post>> violations = new ArrayList<>(
                validator.validate(post, Update.class));
        ExpectedViolation expectedViolation = new ExpectedViolation(
                "id", "must not be null");
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