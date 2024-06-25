package ru.yandex.practicum.catsgram.storage;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import ru.yandex.practicum.catsgram.post.model.Post;
import ru.yandex.practicum.catsgram.post.DbPostStorage;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@ContextConfiguration(classes = {DbPostStorage.class})
class DbPostStorageTest {
    private final DbPostStorage dbPostStorage;

    @Test
    void create() {
        // Имеем 1 пост в БД
        dbPostStorage.create(new Post(
                "new@test.ru",
                Instant.now(),
                "new",
                "https://www.h2database.com/new.png"
        ));
        List<Post> posts = dbPostStorage.getAll();
        assertEquals(2, posts.size());
        assertThat(posts.get(1)).hasFieldOrPropertyWithValue("id", 1L);
        assertThat(posts.get(1)).hasFieldOrPropertyWithValue("author", "new@test.ru");
        assertThat(posts.get(1)).hasFieldOrProperty("creationDate");
        assertThat(posts.get(1)).hasFieldOrPropertyWithValue("description", "new");
        assertThat(posts.get(1)).hasFieldOrPropertyWithValue("photoUrl", "https://www.h2database.com/new.png");
    }

    @Test
    @Sql(scripts = {"/post/test-get-posts.sql"})
    void update() {
        dbPostStorage.update(new Post(
                1L,
                "updated@test.ru",
                Instant.now(),
                "testUPDATE",
                "https://www.h2database.com/updated.png"
        ));

        Post post = dbPostStorage.get(1L);

        assertThat(post).hasFieldOrPropertyWithValue("id", 1L);
        assertThat(post).hasFieldOrPropertyWithValue("author", "updated@test.ru");
        assertThat(post).hasFieldOrProperty("creationDate");
        assertThat(post).hasFieldOrPropertyWithValue("description", "testUPDATE");
        assertThat(post).hasFieldOrPropertyWithValue("photoUrl", "https://www.h2database.com/updated.png");
    }

    @Test
    @Sql(scripts = {"/post/test-get-posts.sql"})
    void get() {
        Post post = dbPostStorage.get(0L);

        assertThat(post).hasFieldOrPropertyWithValue("id", 0L);
        assertThat(post).hasFieldOrPropertyWithValue("author", "aleksandrTEST@ya.ru");
        assertThat(post).hasFieldOrProperty("creationDate");
        assertThat(post).hasFieldOrPropertyWithValue("description", "test");
        assertThat(post).hasFieldOrPropertyWithValue("photoUrl", "https://www.h2database.com/html/images/h2-logo-2.png");
    }

    @Test
    @Sql(scripts = {"/post/test-get-posts.sql"})
    void delete() {
        // Имеем 2 поста в БД

        // Удаляем 1 из них
        dbPostStorage.delete(1L);

        // Проверяем что остался 1 пост
        assertEquals(1, dbPostStorage.getAll().size());
    }

    @Test
    @Sql(scripts = {"/post/test-get-posts.sql"})
    void getAll() {
        List<Post> posts = dbPostStorage.getAll();

        assertEquals(2, posts.size());

        assertThat(posts.get(0)).hasFieldOrPropertyWithValue("id", 0L);
        assertThat(posts.get(0)).hasFieldOrPropertyWithValue("author", "aleksandrTEST@ya.ru");
        assertThat(posts.get(0)).hasFieldOrProperty("creationDate");
        assertThat(posts.get(0)).hasFieldOrPropertyWithValue("description", "test");
        assertThat(posts.get(0)).hasFieldOrPropertyWithValue("photoUrl", "https://www.h2database.com/html/images/h2-logo-2.png");

        assertThat(posts.get(1)).hasFieldOrPropertyWithValue("id", 1L);
        assertThat(posts.get(1)).hasFieldOrPropertyWithValue("author", "aleksandr2TEST@ya.ru");
        assertThat(posts.get(1)).hasFieldOrProperty("creationDate");
        assertThat(posts.get(1)).hasFieldOrPropertyWithValue("description", "test2");
        assertThat(posts.get(1)).hasFieldOrPropertyWithValue("photoUrl", "https://www.h2database.com/html/images/h2-logo-2.png");
    }
}