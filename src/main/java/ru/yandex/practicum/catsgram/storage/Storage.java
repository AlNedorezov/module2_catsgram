package ru.yandex.practicum.catsgram.storage;

import ru.yandex.practicum.catsgram.model.StorageData;

import java.util.List;

public interface Storage<T extends StorageData> {
    void create(T data);

    void update(T data);

    T get(long id);

    void delete(long id);

    List<T> getAll();
}

