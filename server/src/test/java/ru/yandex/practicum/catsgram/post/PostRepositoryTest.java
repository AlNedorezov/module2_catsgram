package ru.yandex.practicum.catsgram.post;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.yandex.practicum.catsgram.post.model.Post;

@DataJpaTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class PostRepositoryTest {
    private final PostRepository postRepository;

    private Post post;

    @BeforeEach
    void setUp() {
        post = new Post();
        post.setId(1L);
        post.setAuthor("test@google.com");
        post.setDescription("description");
        post.setPhotoUrl("https://online.ssidigital.in/p/apachemaven");
    }

    @Test
    void count() {
        postRepository.save(post);
        long result = postRepository.count();
        Assertions.assertEquals(1, result);
    }

    @AfterEach
    void tearDown() {
        postRepository.deleteAll();
    }
}