package com.josharnow.newsaggregator.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LoginUser {
  @NotEmpty
  @Email
  @Size(min = 5, max = 80)
  private String email;

  @NotEmpty
  @Size(min = 8, max = 60)
  private String password;

  // Constructor
  public LoginUser() {}

  // Getters and Setters
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
