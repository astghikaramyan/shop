package am.shop.demo.controller;

import am.shop.demo.dto.UserDto;
import am.shop.demo.entity.UserEntity;
import am.shop.demo.mapper.UserMapper;
import am.shop.demo.security.CurrentUser;
import am.shop.demo.security.UserPrincipal;
import am.shop.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@CrossOrigin
@RestController
public class UserController {
  private UserService userService;
  private UserMapper userMapper;

  @Autowired
  public UserController(UserService userService, UserMapper userMapper){
    this.userService = userService;
    this.userMapper = userMapper;
  }

//  @PostMapping("/api/users")
//  public ResponseEntity<UserDto> addUser(@RequestBody @Valid UserEntity userEntity){
//    try{
//      userEntity = this.userService.addUser(userEntity);
//      if(userEntity != null){
//        return ResponseEntity.ok(this.userMapper.toDto(userEntity));
//      }
//    }catch (Exception e){
//      e.printStackTrace();
//    }
//    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//  }

  @GetMapping("/api/users/_search")
  public ResponseEntity<UserDto> getUser(@RequestParam(name="id") String id){
    try{
      Optional<UserEntity> userEntity = this.userService.getUser(id);
      if(userEntity.isPresent()){
        return ResponseEntity.ok(this.userMapper.toDto(userEntity.get()));
      }
      return ResponseEntity.notFound().build();
    }catch (Exception e){
      e.printStackTrace();
    }
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }


//  @PreAuthorize("hasRole('user')")
  @GetMapping("/api/users")
  @ResponseBody
  public ResponseEntity<UserDto> getUserByEmail(@CurrentUser UserPrincipal userPrincipal){
//    try{
//      UserEntity userEntity = this.userService.getUserByEmail(userPrincipal.getEmail());
//      if(userEntity != null){
//        return ResponseEntity.ok(this.userMapper.toDto(userEntity));
//      }
//      return ResponseEntity.notFound().build();
//    }catch (Exception e){
//      e.printStackTrace();
//    }
//    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    UserEntity userEntity = new UserEntity();
    userEntity.setId(userPrincipal.getId());
    userEntity.setName(userPrincipal.getName());
    userEntity.setEmail(userPrincipal.getEmail());
    userEntity.setPassword(userPrincipal.getPassword());
    Collection<? extends GrantedAuthority> grantedAuthorityList = userPrincipal.getAuthorities();

    Set<String> authorities = new HashSet<>();

    for (GrantedAuthority grantedAuthority : grantedAuthorityList) {
      authorities.add(grantedAuthority.getAuthority());
    }
    userEntity.setRoles(authorities);
    return ResponseEntity.ok(this.userMapper.toDto(userEntity));
  }

//  @GetMapping("/api/users")
//  public ResponseEntity<List<UserDto>> getUsers(){
//    try{
//      Iterable<UserEntity> userEntities = this.userService.getUsers();
//      if(userEntities != null){
//        return ResponseEntity.ok(this.userMapper.toDtoList(userEntities));
//      }
//      return ResponseEntity.notFound().build();
//    }catch (Exception e){
//      e.printStackTrace();
//    }
//    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//  }

  @PutMapping("/api/users")
  public ResponseEntity<UserDto> update(@RequestBody UserEntity userEntity){
    try{
      userEntity = this.userService.updateUser(userEntity);
      if(userEntity != null){
        return ResponseEntity.ok(this.userMapper.toDto(userEntity));
      }
    }catch (Exception e){
      e.printStackTrace();
    }
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @DeleteMapping("/api/users")
  public ResponseEntity delete(@RequestParam(name="id") String id){
    try{
      this.userService.delete(id);
    }catch (Exception e){
      e.printStackTrace();
    }
    return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
  }
//  @PostMapping("/api/users/_loginSearch")
//  public ResponseEntity<UserDto> getByEmailAndPass(@RequestBody UserEntity userEntity){
//    try{
//      Optional<UserEntity> userEntity1 = this.userService.loginUser(userEntity);
//      if(userEntity1.isPresent()){
//        return ResponseEntity.ok(this.userMapper.toDto(userEntity1.get()));
//      }
//      return ResponseEntity.notFound().build();
//    }catch (Exception e){
//      e.printStackTrace();
//    }
//    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//  }
}
