package ru.yandex.practicum.catsgram.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.post.dto.PostDto;
import ru.yandex.practicum.catsgram.post.model.Post;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    PostRepository postRepository;
    PostValidator postValidator;

    @Autowired
    public PostServiceImpl(PostRepository repository, PostValidator validator) {
        this.postRepository = repository;
        this.postValidator = validator;
    }

    public PostDto create(PostDto postDto) {
        final Post post = PostMapper.toPost(postDto);
        postValidator.validate(post);
        postRepository.save(post);
        return PostMapper.toPostDto(post);
    }

    public PostDto update(PostDto postDto) {
        final Post post = PostMapper.toPost(postDto);
        postValidator.validate(post);
        postRepository.save(post);
        return PostMapper.toPostDto(post);
    }

    public List<PostDto> getAll() {
        return postRepository.findAll()
                .stream()
                .map(PostMapper::toPostDto)
                .toList();
    }
}
