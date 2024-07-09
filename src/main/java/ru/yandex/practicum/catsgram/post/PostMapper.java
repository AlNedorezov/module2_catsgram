package ru.yandex.practicum.catsgram.post;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.yandex.practicum.catsgram.post.dto.PostDto;
import ru.yandex.practicum.catsgram.post.model.Post;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostMapper {
    public static PostDto toPostDto(Post post) {
        return new PostDto(
                post.getId(),
                post.getAuthor(),
                post.getCreationDate(),
                post.getDescription(),
                post.getPhotoUrl()
        );
    }

    public static Post toPost(PostDto postDto) {
        Post post = new Post();
        post.setId(postDto.getId());
        post.setAuthor(postDto.getAuthor());
        post.setCreationDate(postDto.getCreationDate());
        post.setDescription(postDto.getDescription());
        post.setPhotoUrl(postDto.getPhotoUrl());
        return post;
    }
}
