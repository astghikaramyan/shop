package am.shop.demo.service;


import am.shop.demo.entity.UserEntity;
import am.shop.demo.exceptions.DatabaseException;

import java.util.Optional;

public interface UserService {
  UserEntity addUser(UserEntity entity) throws DatabaseException;
  Optional<UserEntity> getUser(String id) throws DatabaseException;
  UserEntity updateUser(UserEntity userEntity) throws DatabaseException;
  Iterable<UserEntity> getUsers() throws DatabaseException;
  void delete(String id) throws DatabaseException;
  Optional<UserEntity> loginUser(UserEntity userEntity) throws DatabaseException;
  UserEntity getUserByEmail(String email) throws DatabaseException;
  Optional<UserEntity> getUserByEmailOrName(String email, String name) throws DatabaseException;
  boolean existByUserName(String name) throws DatabaseException;
  boolean existByUserEmail(String email) throws DatabaseException;
}
