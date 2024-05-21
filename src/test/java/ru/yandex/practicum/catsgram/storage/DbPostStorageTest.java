package ru.yandex.practicum.catsgram.storage;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@ContextConfiguration(classes = {DbPostStorage.class})
class DbPostStorageTest {
    private final DbPostStorage dbPostStorage;

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void get() {
    }

    @Test
    void delete() {
    }

    @Test
    void getAll() {
    }
}