package ru.yandex.practicum.catsgram.post.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.yandex.practicum.catsgram.common.Update;

import java.time.Instant;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostDto {
    @NotNull(groups = {Update.class})
    Long id;
    @NotBlank
    private String author; // автор
    private Instant creationDate = Instant.now(); // дата создания
    @Size(min = 5)
    private String description; // описание
    private String photoUrl;
}
