package ru.yandex.practicum.catsgram.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.common.Update;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {
	private final PostClient postClient;

	@GetMapping("/posts")
	public ResponseEntity<Object> getAllPosts() {
		log.info("Get all posts");
		return postClient.getAll();
	}

	@PostMapping(value = "/post")
	public ResponseEntity<Object> createPost(
			@RequestBody PostDto postDto) {
		log.info("Creating post {}", postDto);
		return postClient.createPost(postDto);
	}

	@PutMapping(value = "/post")
	public ResponseEntity<Object> update(@RequestBody @Validated(Update.class) PostDto postDto) {
		log.info("Updating post {}", postDto);
		return postClient.updatePost(postDto);
	}
}
