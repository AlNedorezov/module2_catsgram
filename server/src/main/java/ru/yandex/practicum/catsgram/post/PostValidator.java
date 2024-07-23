package ru.yandex.practicum.catsgram.post;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.catsgram.exception.ValidationException;
import ru.yandex.practicum.catsgram.post.model.Post;

@Component
public class PostValidator {
    private static final int MAX_NAME_SIZE = 200;

    public void validate(Post post) {
        if (post.getAuthor() == null || post.getAuthor().isEmpty()) {
            throw new ValidationException("Post author invalid");
        }
        if (post.getDescription() != null && post.getDescription().length() > MAX_NAME_SIZE) {
            throw new ValidationException("Film description invalid");
        }
    }
}
