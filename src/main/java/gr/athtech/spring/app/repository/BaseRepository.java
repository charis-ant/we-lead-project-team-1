package gr.athtech.spring.app.repository;

import gr.athtech.spring.app.model.BaseModel;

import java.util.List;

public interface BaseRepository<T extends BaseModel, K> {
    T create(T item);

    List<T> createAll(List<T> items);

    List<T> createAll(T... items);

    void update(T item);

    void delete(T item);

    void deleteById(K id);

    T get(K id);

    boolean exists(T item);

    List<T> findAll();

    Long count();
}
