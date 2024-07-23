package ru.yandex.practicum.catsgram.post.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Entity
@Table(name = "posts")
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    Long id;
    @Column(name = "author", nullable = false, length = 256)
    private String author; // автор
    @Column(name = "creation_date", nullable = false)
    private Instant creationDate = Instant.now(); // дата создания
    @Column(name = "description", nullable = false, length = 256)
    private String description; // описание
    @Column(name = "photo_url", nullable = false, length = 256)
    private String photoUrl; // url-адрес фотографии

    public Post(String author, String description, String photoUrl) {
        this.author = author;
        this.creationDate = Instant.now();
        this.description = description;
        this.photoUrl = photoUrl;
    }
}