package ru.yandex.practicum.catsgram.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exception.ValidationException;
import ru.yandex.practicum.catsgram.common.Storage;
import ru.yandex.practicum.catsgram.post.dto.PostDto;
import ru.yandex.practicum.catsgram.post.model.Post;

import java.util.List;

@Service
public class PostService {

    Storage<Post> postStorage;

    @Autowired
    public PostService(Storage<Post> storage) {
        this.postStorage = storage;
    }

    public Post create(PostDto postDto) {
        final Post post = PostMapper.toPost(postDto);
        validate(post);
        postStorage.create(post);
        return post;
    }

    public Post update(PostDto postDto) {
        final Post post = PostMapper.toPost(postDto);
        validate(post);
        postStorage.update(post);
        return post;
    }

    public List<PostDto> getAll() {
        return postStorage.getAll()
                .stream()
                .map(PostMapper::toPostDto)
                .toList();
    }

    private static final int MAX_NAME_SIZE = 200;

    void validate(Post post) {
        if (post.getAuthor() == null || post.getAuthor().isEmpty()) {
            throw new ValidationException("Post author invalid");
        }
        if (post.getDescription() != null && post.getDescription().length() > MAX_NAME_SIZE) {
            throw new ValidationException("Film description invalid");
        }
    }
}
