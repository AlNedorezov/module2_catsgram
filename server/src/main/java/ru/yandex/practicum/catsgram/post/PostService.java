package ru.yandex.practicum.catsgram.post;

import ru.yandex.practicum.catsgram.post.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> getAll();

    PostDto create(PostDto postDto);

    PostDto update(PostDto postDto);
}
