package com.portada.portadaapp.demo;

import com.portada.portadaapp.models.TodoItem;
import com.portada.portadaapp.repositories.TodoItemRepository;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class TodoItemService {

  @Autowired
  private TodoItemRepository todoItemRepository;

  @Nullable
  public TodoItem getById(Long id) {
    return todoItemRepository
        .findById(id)
        .orElse(null);
  }

  public List<TodoItem> getAll() {
    return todoItemRepository.findAll().stream().toList();
  }

  public TodoItem save(final TodoItem todoItem) {
    if (todoItem.getId() == null) {
      todoItem.setCreatedAt(Instant.now());
    }
    todoItem.setUpdatedAt(Instant.now());

    return todoItemRepository.save(todoItem);
  }

  public void delete(final TodoItem todoItem) {
    todoItemRepository.delete(todoItem);
  }

}