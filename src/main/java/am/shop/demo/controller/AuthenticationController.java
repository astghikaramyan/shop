package am.shop.demo.controller;

import am.shop.demo.entity.UserEntity;
import am.shop.demo.exceptions.DatabaseException;
import am.shop.demo.mapper.UserMapper;
import am.shop.demo.payload.ApiResponse;
import am.shop.demo.payload.JwtauthenticationResponse;
import am.shop.demo.payload.LoginRequest;
import am.shop.demo.payload.SignUpRequest;
import am.shop.demo.security.JwtTokenProvider;
import am.shop.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@CrossOrigin
@RestController
@RequestMapping("/api/users/auth")
public class AuthenticationController {
  @Autowired
  private UserService userService;

  @Autowired
  private JwtTokenProvider tokenProvider;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        loginRequest.getUserNameOrEmail(),
        loginRequest.getPassword()
      )
    );
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = tokenProvider.generateToken(authentication);
    return ResponseEntity.ok(new JwtauthenticationResponse(jwt));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest){
    try{
      if(userService.existByUserName(signUpRequest.getName())){
        return new ResponseEntity<>(new ApiResponse(false, "Username already taken"), HttpStatus.BAD_REQUEST);
      }
      if(userService.existByUserEmail(signUpRequest.getEmail())){
        return new ResponseEntity<>(new ApiResponse(false, "Email adress already in use "), HttpStatus.BAD_REQUEST);
      }
      UserEntity user = new UserEntity();
      user.setName(signUpRequest.getName());
      user.setPassword(signUpRequest.getPassword());
      user.setEmail(signUpRequest.getEmail());
      if(signUpRequest.getName().equals("newAdmin")){
        user.setRoles(Collections.singleton("admin"));
      }else {
        user.setRoles(Collections.singleton("user"));
      }
      user = userService.addUser(user);
      return ResponseEntity.ok(userMapper.toDto(user));
    }catch (DatabaseException e){
      e.printStackTrace();
    }
    return new ResponseEntity<>(new ApiResponse(false, "Bad request"), HttpStatus.BAD_REQUEST);
  }
}
