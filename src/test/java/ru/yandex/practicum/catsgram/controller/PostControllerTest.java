package ru.yandex.practicum.catsgram.controller;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.catsgram.model.Post;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PostControllerTest {

    static PostController postController = new PostController();

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
}