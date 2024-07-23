package ru.yandex.practicum.catsgram.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.yandex.practicum.catsgram.client.BaseClient;

@Service
public class PostClient extends BaseClient {
	private static final String API_PREFIX = "";

	@Autowired
	public PostClient(@Value("${catsgram-server.url}") String serverUrl, RestTemplateBuilder builder) {
		super(
				builder
						.uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
						.requestFactory(() -> new HttpComponentsClientHttpRequestFactory())
						.build()
		);
	}

	public ResponseEntity<Object> getAll() {
		return get("/posts");
	}

	public ResponseEntity<Object> createPost(PostDto postDto) {
		return post("/post", postDto);
	}

	public ResponseEntity<Object> updatePost(PostDto postDto) {
		return put("/post", postDto);
	}
}
