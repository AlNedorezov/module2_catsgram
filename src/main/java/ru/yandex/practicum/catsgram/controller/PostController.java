package ru.yandex.practicum.catsgram.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.Update;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class PostController {
    private static final int MAX_NAME_SIZE = 200;

    private long idGenerator = 0L;
    private final Map<Long, Post> posts = new HashMap<>();

    @GetMapping("/posts")
    public List<Post> findAll() {
        return new ArrayList<>(posts.values());
    }

    @PostMapping(value = "/post")
    public void create(@Valid @RequestBody Post post) {
        validate(post);
        post.setId(++idGenerator);
        posts.put(post.getId(), post);
        log.info("New post created");
    }

    @PutMapping(value = "/post")
    public void update(
            @Validated(Update.class) // <-- см.
            @RequestBody Post post) {
        validate(post);
        posts.put(post.getId(), post);
        log.info("New post created");
    }

    void validate(Post post) {
        if (post.getAuthor() == null || post.getAuthor().isEmpty()) {
            throw new ValidationException("Post author invalid");
        }
        if (post.getDescription() != null && post.getDescription().length() > MAX_NAME_SIZE) {
            throw new ValidationException("Film description invalid");
        }
    }
}