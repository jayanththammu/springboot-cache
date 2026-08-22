package com.cacheing.service;

import com.cacheing.model.User;
import com.cacheing.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository repository;

  @Cacheable(value = "users", key = "#id")
  public User getUserById(Long id) {
    System.out.println("Get user by Id:fetching....");
    User user = repository
      .findById(id)
      .orElseThrow(() -> new RuntimeException("User Not Found"));
    System.out.println("fetched sucessfully");

    return user;
  }

  //   @CacheEvict(value = "users,allusers", key = "#id")
  @Caching(
    evict = {
      @CacheEvict(value = "users", key = "#id"),
      @CacheEvict(value = "allusers", allEntries = true),
    }
  )
  public void deleteUser(Long id) {
    System.out.println("Deleting user with id" + id);
    repository.deleteById(id);
    System.out.println("Deleeted....");
  }

  @Cacheable(value = "allusers")
  public List<User> getAllUsers() {
    System.out.println("Fetching from db...");
    List<User> allUsers = repository.findAll();
    System.out.println("fetched successfully..........");
    return allUsers;
  }
}
