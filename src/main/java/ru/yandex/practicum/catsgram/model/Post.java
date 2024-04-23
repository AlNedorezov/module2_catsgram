package ru.yandex.practicum.catsgram.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Instant;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Post extends StorageData {
    @NotBlank
    private String author; // автор
    private final Instant creationDate = Instant.now(); // дата создания
    @Size(min = 5)
    private String description; // описание
    private String photoUrl; // url-адрес фотографии
}