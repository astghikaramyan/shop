package am.shop.demo.security;

import lombok.Data;

import java.util.Set;

@Data
public class User {

  private String name;

  private String email;

  private String password;

  private Set<String> roles;

  public User(String name, String email, String  password,Set<String> roles){
    this.name = name;
    this.email = email;
    this.password = password;
    this.roles = roles;
  }
}
