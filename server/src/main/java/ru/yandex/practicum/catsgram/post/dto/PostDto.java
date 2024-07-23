package ru.yandex.practicum.catsgram.post.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostDto {
    Long id;
    private String author; // автор
    private Instant creationDate = Instant.now(); // дата создания
    private String description; // описание
    private String photoUrl;

    public PostDto(String author, String description, String photoUrl) {
        this.author = author;
        this.creationDate = Instant.now();
        this.description = description;
        this.photoUrl = photoUrl;
    }
}
