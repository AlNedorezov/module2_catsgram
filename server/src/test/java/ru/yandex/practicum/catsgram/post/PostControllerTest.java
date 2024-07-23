package ru.yandex.practicum.catsgram.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
@AutoConfigureMockMvc
class PostControllerTest {
    @MockBean
    private PostService postService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void findAll() throws Exception {
        // given
        when(postService.getAll()).thenReturn(Collections.emptyList());

        // when + then
        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
        verify(postService, times(1)).getAll();
    }
}