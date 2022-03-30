package com.josharnow.newsaggregator.services;

import java.util.Optional;

import com.josharnow.newsaggregator.models.LoginUser;
import com.josharnow.newsaggregator.models.User;
import com.josharnow.newsaggregator.repositories.UserRepository;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class UserService {
  private UserRepository userRepo;

  public UserService(UserRepository userRepo) {
    this.userRepo = userRepo;
  }

  // REGISTRATION
  public User register(User newUser, BindingResult result) {
    if (userRepo.findByEmail(newUser.getEmail()).isPresent()) {
      result.rejectValue("email", "Unique", "This email is already in use!");
    }
    if (!newUser.getPassword().equals(newUser.getPasswordConfirm())) {
      // result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
      result.rejectValue("passwordConfirm", "Matches", "The Confirm Password must match Password!");
    }
    if (result.hasErrors()) {
      return null;
    } else {
      String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
      newUser.setPassword(hashed);
      return userRepo.save(newUser);
    }
  }

  // LOGIN
  public User login(LoginUser newLogin, BindingResult result) {
    if (result.hasErrors()) {
      return null;
    }
    Optional<User> potentialUser = userRepo.findByEmail(newLogin.getEmail());
    if (!potentialUser.isPresent()) {
      result.rejectValue("email", "Unique", "Unknown email!");
      return null;
    }
    User user = potentialUser.get();
    if (!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
      result.rejectValue("password", "Matches", "Invalid Password!");
    }
    if (result.hasErrors()) {
      return null;
    } else {
      return user;
    }
  }
}
