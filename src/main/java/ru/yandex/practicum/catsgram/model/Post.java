package ru.yandex.practicum.catsgram.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Post extends StorageData {
    private String author; // автор
    private Instant creationDate = Instant.now(); // дата создания
    private String description; // описание
    private String photoUrl; // url-адрес фотографии

    public Post(long postId, String author, Instant creationDate, String description, String photoUrl) {
        super(postId);
        this.author = author;
        this.creationDate = creationDate;
        this.description = description;
        this.photoUrl = photoUrl;
    }

    public Post(String author, String description, String photoUrl) {
        this.author = author;
        this.creationDate = Instant.now();
        this.description = description;
        this.photoUrl = photoUrl;
    }
}