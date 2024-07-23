package ru.yandex.practicum.catsgram.post.dto;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@JsonTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class PostDtoTest {
    private final JacksonTester<PostDto> json;

    @Test
    void testSerialize() throws Exception {
        PostDto postDto = new PostDto();
        postDto.setId(1L);
        postDto.setAuthor("test@google.com");
        postDto.setDescription("description");
        postDto.setPhotoUrl("https://online.ssidigital.in/p/apachemaven");

        JsonContent<PostDto> result = json.write(postDto);

        assertThat(result).hasJsonPath("$.id")
                .hasJsonPath("$.author")
                .hasJsonPath("$.creationDate")
                .hasJsonPath("$.description")
                .hasJsonPath("$.photoUrl");

        assertThat(result).extractingJsonPathNumberValue("$.id")
                .satisfies(id -> assertThat(id.longValue()).isEqualTo(postDto.getId()));
        assertThat(result).extractingJsonPathStringValue("$.author")
                .satisfies(author -> assertThat(author).isEqualTo(postDto.getAuthor()));
        assertThat(result).extractingJsonPathStringValue("$.creationDate")
                .satisfies(creationDate -> assertThat(creationDate).isNotNull());
        assertThat(result).extractingJsonPathStringValue("$.description")
                .satisfies(description -> assertThat(description).isEqualTo(postDto.getDescription()));
        assertThat(result).extractingJsonPathStringValue("$.photoUrl")
                .satisfies(photoUrl -> assertThat(photoUrl).isEqualTo(postDto.getPhotoUrl()));
    }
}