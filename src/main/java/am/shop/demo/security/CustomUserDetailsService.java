package am.shop.demo.security;

import am.shop.demo.entity.UserEntity;
import am.shop.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  UserRepo userRepo;

  // Let people login with either username or email
  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException{
    try{
      Optional<UserEntity> userEntity = this.userRepo.findByEmailOrName(s, s);
      return UserPrincipal.create(userEntity.get());
    }catch (UsernameNotFoundException e){
      throw  new UsernameNotFoundException("User not found with username or email " + s);
    }
  }

//  // This method is used by JWTAuthenticationFilter
  public UserDetails loadUserById(String id){
    try{
      Optional<UserEntity> userEntity = this.userRepo.findById(id);
      return UserPrincipal.create(userEntity.get());
    }catch (UsernameNotFoundException e){
      throw  new UsernameNotFoundException("User not found with id " + id);
    }
  }

  // This method is used by JWTAuthenticationFilter
  public UserDetails loadUserByEmail(String email){
    try{
      UserEntity userEntity = this.userRepo.findByEmail(email);
      return UserPrincipal.create(userEntity);
    }catch (UsernameNotFoundException e){
      throw  new UsernameNotFoundException("User not found with email " + email);
    }
  }
}
