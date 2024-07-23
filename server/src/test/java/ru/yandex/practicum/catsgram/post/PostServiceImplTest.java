package ru.yandex.practicum.catsgram.post;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.catsgram.post.dto.PostDto;
import ru.yandex.practicum.catsgram.post.model.Post;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PostServiceImplTest {

    private PostServiceImpl postService;
    private PostRepository postRepository;
    private PostValidator postValidator = new PostValidator();

    private Post post;
    private PostDto postDto;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        when(postRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        postService = new PostServiceImpl(postRepository, postValidator);
        post = new Post();
        post.setId(1L);
        post.setAuthor("test@google.com");
        post.setDescription("description");
        post.setPhotoUrl("https://online.ssidigital.in/p/apachemaven");
        postDto = PostMapper.toPostDto(post);
    }

    @Test
    void create() {
        PostDto result = postService.create(postDto);
        Assertions.assertNotNull(result);
        verify(postRepository, times(1)).save(any());
    }

    @Test
    void update() {
        PostDto result = postService.update(postDto);
        Assertions.assertNotNull(result);
        verify(postRepository, times(1)).save(any());
    }

    @Test
    void getAll() {
        List<Post> posts = new ArrayList<>();
        posts.add(post);
        when(postRepository.findAll()).thenReturn(posts);
        List<PostDto> result = postService.getAll();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        verify(postRepository, times(1)).findAll();
    }
}