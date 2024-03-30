package com.example.restapi.service.impl;

import com.example.restapi.service.CrudService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

@RequiredArgsConstructor
public abstract class CrudServiceImpl<T> implements CrudService<T> {
    private final JpaRepository<T, Long> repository;
    private final String entityName;

    @Override
    public T findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("No entity: '%s' with id: %d", entityName, id)
                ));
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
