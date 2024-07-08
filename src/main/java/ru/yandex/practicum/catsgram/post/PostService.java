package ru.yandex.practicum.catsgram.post;

import ru.yandex.practicum.catsgram.post.dto.PostDto;
import ru.yandex.practicum.catsgram.post.model.Post;

import java.util.List;

public interface PostService {
    List<PostDto> getAll();

    Post create(PostDto postDto);

    Post update(PostDto postDto);
}
