package am.shop.demo.security;

import am.shop.demo.entity.UserEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class UserPrincipal implements UserDetails {

  private String id;

  private String name;

  private String email;

  private String password;

  private Collection<? extends GrantedAuthority> authorities;

  public UserPrincipal(String id, String name, String email, String password, Collection<? extends GrantedAuthority> authorities){
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.authorities = authorities;
  }

  public static UserPrincipal create(UserEntity userEntity){
    List<GrantedAuthority> authorities = userEntity.getRoles().stream().map(role->
      new SimpleGrantedAuthority(role)).collect(Collectors.toList());
    return new UserPrincipal(
      userEntity.getId(),
      userEntity.getName(),
      userEntity.getEmail(),
      userEntity.getPassword(),
      authorities
    );
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return name;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
