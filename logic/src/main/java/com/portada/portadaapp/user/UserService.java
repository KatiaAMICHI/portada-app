package com.portada.portadaapp.user;

import com.portada.portadaapp.models.TodoItem;
import com.portada.portadaapp.repositories.TodoItemRepository;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Nullable
  public User getByEmail(final String email) {
    return userRepository.findByEmail(email).orElse(null);
  }

}