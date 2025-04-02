package com.example.laptopstorebackend.controller;

import com.example.laptopstorebackend.dto.response.ApiResponse;
import com.example.laptopstorebackend.service.GenericServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public abstract class GenericController<T, ID> {
    private final GenericServiceImpl<T, ID> service;

    public GenericController(GenericServiceImpl<T, ID> service) {
        this.service = service;
    }

    @GetMapping
    public List<T> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<T> getById(@PathVariable ID id) {
        return service.findById(id);
    }

    @PostMapping
    public T create(@RequestBody T entity) {
        return service.save(entity);
    }

    @PutMapping("/{id}")
    public T update(@PathVariable ID id, @RequestBody T entity) {
        return service.save(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable ID id) {
        service.deleteById(id);
    }

}
