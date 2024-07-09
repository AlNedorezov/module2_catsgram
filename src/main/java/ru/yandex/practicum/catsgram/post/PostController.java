package ru.yandex.practicum.catsgram.post;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.catsgram.common.Update;

import jakarta.validation.Valid;
import ru.yandex.practicum.catsgram.post.dto.PostDto;

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
    public List<PostDto> findAll() {
        return service.getAll();
    }

    @PostMapping(value = "/post")
    public void create(@Valid @RequestBody PostDto post) {
        log.info("creating a new post");
        service.create(post);
    }

    @PutMapping(value = "/post")
    public void update(
            @Validated(Update.class) // <-- см.
            @RequestBody PostDto post) {
        log.info("New post created");
        service.update(post);
    }
}