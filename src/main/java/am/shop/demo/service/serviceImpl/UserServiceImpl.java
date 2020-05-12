package am.shop.demo.service.serviceImpl;

import am.shop.demo.entity.UserEntity;
import am.shop.demo.exceptions.DatabaseException;
import am.shop.demo.repo.UserRepo;
import am.shop.demo.service.UserService;
import com.arangodb.ArangoDBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private PasswordEncoder passwordEncoder;
  private UserRepo userRepo;

  @Autowired
  public UserServiceImpl(UserRepo userRepo){
    this.userRepo = userRepo;
  }

  @Override
  public UserEntity addUser(UserEntity entity)  throws DatabaseException {
    entity.setPassword(this.passwordEncoder.encode(entity.getPassword()));
//    entity.setPassword(EncryptionUtil.encrypt(entity.getPassword()));
    try{
      return this.userRepo.save(entity);
    }catch (ArangoDBException e){
      throw new DatabaseException(e.getMessage());
    }
  }

  @Override
  public Optional<UserEntity> getUser(String id) throws DatabaseException{
    try{
      return this.userRepo.findById(id);
    }catch (ArangoDBException e){
      throw new DatabaseException(e.getMessage());
    }
  }

  @Override
  public UserEntity updateUser(UserEntity userEntity) throws DatabaseException{
    userEntity.setPassword(this.passwordEncoder.encode(userEntity.getPassword()));
//    userEntity.setPassword(EncryptionUtil.encrypt(userEntity.getPassword()));
    try{
      return this.userRepo.save(userEntity);
    }catch (ArangoDBException e){
      throw new DatabaseException(e.getMessage());
    }
  }

  @Override
  public Iterable<UserEntity> getUsers() throws DatabaseException{
    try{
      return this.userRepo.findAll();
    }catch (ArangoDBException e){
      throw new DatabaseException(e.getMessage());
    }
  }

  @Override
  public void delete(String id) throws DatabaseException{
    try{
      this.userRepo.deleteById(id);
    }catch (ArangoDBException e){
      throw new DatabaseException(e.getMessage());
    }
  }

  @Override
  public Optional<UserEntity> loginUser(UserEntity userEntity) throws DatabaseException{
    userEntity.setPassword(this.passwordEncoder.encode(userEntity.getPassword()));
//    userEntity.setPassword(EncryptionUtil.encrypt(userEntity.getPassword()));
    try{
      return this.userRepo.findByEmailAndPassword(userEntity.getEmail(), userEntity.getPassword());
    }catch (ArangoDBException e){
      throw new DatabaseException(e.getMessage());
    }
  }

  @Override
  public UserEntity getUserByEmail(String email) throws DatabaseException {
    try{
      return this.userRepo.findByEmail(email);
    }catch (ArangoDBException e){
      throw new DatabaseException(e.getMessage());
    }
  }

  @Override
  public Optional<UserEntity> getUserByEmailOrName(String email, String name) throws DatabaseException {
    try{
      return this.userRepo.findByEmailOrName(email, name);
    }catch (ArangoDBException e){
      throw new DatabaseException(e.getMessage());
    }
  }

  @Override
  public boolean existByUserName(String name) throws DatabaseException {
    try{
      return this.userRepo.existsUserEntityByName(name);
    }catch (ArangoDBException e){
      throw new DatabaseException(e.getMessage());
    }
  }

  @Override
  public boolean existByUserEmail(String email) throws DatabaseException {
    try{
      return this.userRepo.existsUserEntityByEmail(email);
    }catch (ArangoDBException e){
      throw new DatabaseException(e.getMessage());
    }
  }

}
//http://localhost:8529/_db/shop/_admin/aardvark/index.html#collection/users/documents/1
