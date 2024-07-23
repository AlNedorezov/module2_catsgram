package ru.yandex.practicum.catsgram.post;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yandex.practicum.catsgram.post.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
