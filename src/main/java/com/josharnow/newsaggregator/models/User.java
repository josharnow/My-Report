package com.josharnow.newsaggregator.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotEmpty
  @Size(min = 1, max = 30)
  private String firstName;

  @NotEmpty
  @Size(min = 1, max = 50)
  private String lastName;
  
  @NotEmpty
  @Email
  @Size(min = 5, max = 80)
  private String email;
  
  @NotEmpty
  @Size(min = 1, max = 50)
  private String handle;
  
  @NotEmpty
  @Min(13)
  private Integer age;
  
  @NotEmpty
  @Size(min = 8, max = 60)
  private String password;
  
  @Transient
  @NotEmpty
  @Size(min = 8, max = 60)
  private String passwordConfirm;

  @Column(updatable = false)
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date createdAt;
  
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date updatedAt;

  // Database relationship type
  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private List<Article> articles;

  // Constructors
  public User () {}

  public User(@NotEmpty @Size(min = 1, max = 30) String firstName, @NotEmpty @Size(min = 1, max = 50) String lastName,
      @NotEmpty @Email @Size(min = 5, max = 80) String email, @NotEmpty @Size(min = 1, max = 50) String handle,
      @NotEmpty @Min(13) Integer age, @NotEmpty @Size(min = 8, max = 60) String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.handle = handle;
    this.age = age;
    this.password = password;
  }

  public User(@NotEmpty @Size(min = 1, max = 30) String firstName, @NotEmpty @Size(min = 1, max = 50) String lastName,
      @NotEmpty @Email @Size(min = 5, max = 80) String email, @NotEmpty @Size(min = 1, max = 50) String handle,
      @NotEmpty @Min(13) Integer age, @NotEmpty @Size(min = 8, max = 60) String password, List<Article> articles) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.handle = handle;
    this.age = age;
    this.password = password;
    this.articles = articles;
  }

  // Getters and Setters
  public Long getId() {
    return id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public String getFirstName() {
    return firstName;
  }
  
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  
  public String getLastName() {
    return lastName;
  }
  
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  
  public String getHandle() {
    return handle;
  }
  
  public void setHandle(String handle) {
    this.handle = handle;
  }
  
  public Integer getAge() {
    return age;
  }
  
  public void setAge(Integer age) {
    this.age = age;
  }
  
  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  public String getPasswordConfirm() {
    return passwordConfirm;
  }
  
  public void setPasswordConfirm(String passwordConfirm) {
    this.passwordConfirm = passwordConfirm;
  }
  
  public Date getCreatedAt() {
    return createdAt;
  }
  
  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }
  
  public Date getUpdatedAt() {
    return updatedAt;
  }
  
  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }
  
  public List<Article> getArticles() {
    return articles;
  }
  
  public void setArticles(List<Article> articles) {
    this.articles = articles;
  }

  
  @PrePersist
  protected void onCreate() {
    this.createdAt = new Date();
  }

  @PreUpdate
  protected void onUpdate() {
    this.updatedAt = new Date();
  }

  
  
  
}
