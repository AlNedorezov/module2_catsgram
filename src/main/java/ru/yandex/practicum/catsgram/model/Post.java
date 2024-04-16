package ru.yandex.practicum.catsgram.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @NotNull(groups = {Update.class})
    Long id;
    @NotBlank
    private String author; // автор
    private final Instant creationDate = Instant.now(); // дата создания
    @Size(min = 5)
    private String description; // описание
    private String photoUrl; // url-адрес фотографии

    public Post(String author, String description, String photoUrl) {
        this.author = author;
        this.description = description;
        this.photoUrl = photoUrl;
    }
}