package com.smartrepetition.backend.services;

import com.smartrepetition.backend.models.User;
import com.smartrepetition.backend.repositories.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User save(User user) {
    return userRepository.save(user);
  }

  public User getUserById(Long userId) {
    return userRepository.findById(userId).orElse(null);
  }

  public User getUserByUserName(String username) {
    return userRepository.findByUsername(username);
  }

  public User getUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  public int countUsers() {
    return userRepository.findAll().size();
  }

  public List<User> getAll() {
    return userRepository.findAll();
  }
}
