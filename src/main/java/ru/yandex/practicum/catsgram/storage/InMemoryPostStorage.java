package ru.yandex.practicum.catsgram.storage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.catsgram.exception.IncorrectObjectStructureException;
import ru.yandex.practicum.catsgram.exception.NotFoundException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.StorageData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class InMemoryPostStorage extends StorageData implements PostStorage {
    private final HashMap<Long, Post> storage = new HashMap<>();
    private long idGenerator = 0L;

    @Override
    public void create(Post data) {
        validateIdIsSpecified(data);
        data.setId(++idGenerator);
        storage.put(data.getId(), data);
    }

    @Override
    public void update(Post data) {
        validateIdIsSpecified(data);
        if (!storage.containsKey(data.getId())) {
            throw new NotFoundException("post with id=" + data.getId() + " does not exist");
        }
    }

    @Override
    public Post get(long id) {
        final Post post = storage.get(id);
        if (post == null) {
            throw new NotFoundException("post with id=" + id + " does not exist");
        }
        return post;
    }

    @Override
    public void delete(long id) {
        storage.remove(id);
    }

    @Override
    public List<Post> getAll() {
        return new ArrayList<>(storage.values());
    }

    protected void validateIdIsSpecified(Post data) {
        if (data.getId() == null) {
            throw new IncorrectObjectStructureException("id is not specified");
        }
    }
}
