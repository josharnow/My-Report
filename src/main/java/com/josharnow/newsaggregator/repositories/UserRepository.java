package com.josharnow.newsaggregator.repositories;

import java.util.Optional;

import com.josharnow.newsaggregator.models.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
  Optional<User> findByEmail(String email);
}
