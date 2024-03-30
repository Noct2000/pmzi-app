package com.example.restapi.service;

import java.util.List;

public interface CrudService<T> {
    T findById(Long id);

    List<T> findAll();

    T save(T entity);

    void deleteById(Long id);

    void deleteAll();
}
