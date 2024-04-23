package ru.yandex.practicum.catsgram.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.Update;
import ru.yandex.practicum.catsgram.service.PostService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
public class PostController {
    PostService service;

    @Autowired
    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping("/posts")
    public List<Post> findAll() {
        return service.getAll();
    }

    @PostMapping(value = "/post")
    public void create(@Valid @RequestBody Post post) {
        log.info("creating a new post");
        service.create(post);
    }

    @PutMapping(value = "/post")
    public void update(
            @Validated(Update.class) // <-- см.
            @RequestBody Post post) {
        log.info("New post created");
        service.update(post);
    }
}