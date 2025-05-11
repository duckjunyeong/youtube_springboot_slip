package my_slipp.slipp.web.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "USERID", nullable = false)
  private String userId;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "PASSWORD", nullable = false)
  private String password;

  @Column(name = "EMAIL", nullable = false)
  private String email;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "User{" +
        "userId='" + userId + '\'' +
        ", password='" + password + '\'' +
        ", name='" + name + '\'' +
        ", email='" + email + '\'' +
        '}';
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void update(User updateUser) {
    this.userId = updateUser.getUserId();
    this.name = updateUser.getName();
    this.password = updateUser.getPassword();
    this.email = updateUser.getEmail();
  }
}
