package com.smartrepetition.backend.controllers;

import com.smartrepetition.backend.models.User;
import com.smartrepetition.backend.services.UserService;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/users")
  public List<User> allUsers() {
    return userService.getAll();
  }

  @GetMapping("/users/{email:^\\d*[a-zA-Z].*$}")
  public User checkEmail(@PathVariable String email) {
    return userService.getUserByEmail(email);
  }

  @GetMapping("/users/{id:[0-9]+}")
  public User user(@PathVariable long id) {
    return userService.getUserById(id);
  }

  @PostMapping("/users")
  public User addUser(@RequestBody User user) {
    return userService.save(user);
  }
}
