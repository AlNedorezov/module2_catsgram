package ru.yandex.practicum.catsgram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exception.ValidationException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.storage.Storage;

import java.util.List;

@Service
public class PostService {

    Storage<Post> postStorage;

    @Autowired
    public PostService(Storage<Post> storage) {
        this.postStorage = storage;
    }

    public Post create(Post post) {
        validate(post);
        postStorage.create(post);
        return post;
    }

    public Post update(Post post) {
        validate(post);
        postStorage.update(post);
        return post;
    }

    public List<Post> getAll() {
        return postStorage.getAll();
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
