package ru.yandex.practicum.catsgram.validator;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.catsgram.exception.ValidationException;
import ru.yandex.practicum.catsgram.post.PostValidator;
import ru.yandex.practicum.catsgram.post.model.Post;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PostValidatorTest {

    PostValidator validator = new PostValidator();

    @Test
    void validatePostOk() {
        final Post validPost = new Post(
                "name@mail.ru",
                "test post",
                "https://online.ssidigital.in/p/apachemaven"
        );
        validator.validate(validPost);
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
                () -> validator.validate(post)
        );
        assertEquals(
                "Post author invalid",
                exception.getMessage()
        );
    }
}