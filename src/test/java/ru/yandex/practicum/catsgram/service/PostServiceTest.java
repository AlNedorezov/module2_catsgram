package ru.yandex.practicum.catsgram.service;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.catsgram.exception.ValidationException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.storage.InMemoryPostStorage;
import ru.yandex.practicum.catsgram.storage.Storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PostServiceTest {
    Storage<Post> postStorage = new InMemoryPostStorage();
    PostService service = new PostService(postStorage);

    @Test
    void validatePostOk() {
        final Post validPost = new Post(
                "name@mail.ru",
                "test post",
                "https://online.ssidigital.in/p/apachemaven"
        );
        service.validate(validPost);
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
                () -> service.validate(post)
        );
        assertEquals(
                "Post author invalid",
                exception.getMessage()
        );
    }
}