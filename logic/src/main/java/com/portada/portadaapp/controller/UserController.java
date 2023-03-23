package com.portada.portadaapp.controller;

import com.portada.portadaapp.user.User;
import com.portada.portadaapp.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

  final private UserRepository userRepository;

  @GetMapping()
  public ResponseEntity<List<User>> findAllUsers() {
    return ResponseEntity.ok(userRepository.findAll());
  }


  @GetMapping("/{id}")
  public ResponseEntity<User> register(@PathVariable("id") final Integer id) {
    final User userDB = userRepository.findById(id).orElseThrow();

    return ResponseEntity.ok(userDB);
  }

  @GetMapping("/delete/{id}")
  public ResponseEntity.BodyBuilder deleteUser(@PathVariable("id") final Integer id) {
    final User userDB = userRepository.findById(id).orElseThrow();

    userRepository.delete(userDB);
    return ResponseEntity.ok();
  }

}
