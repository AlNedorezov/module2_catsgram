package ru.yandex.practicum.catsgram.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.catsgram.common.Update;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final PostClient postClient;
    private final PostProducer postProducer;

    @GetMapping("/posts")
    public ResponseEntity<Object> getAllPosts() {
        log.info("Get all posts");
        return postClient.getAll();
    }

    @PostMapping(value = "/post")
    public ResponseEntity<Object> createPost(
            @RequestBody PostDto postDto) throws PulsarClientException {
        log.info("Creating post {}", postDto);
        ResponseEntity<Object> response = postClient.createPost(postDto);
        if (response.getStatusCode().is2xxSuccessful()) {
            postProducer.sendPostEvent(postDto);
        }
        return response;
    }

    @PutMapping(value = "/post")
    public ResponseEntity<Object> update(@RequestBody @Validated(Update.class) PostDto postDto) {
        log.info("Updating post {}", postDto);
        return postClient.updatePost(postDto);
    }
}
