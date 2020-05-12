package am.shop.demo.repo;

import am.shop.demo.entity.UserEntity;
import com.arangodb.springframework.repository.ArangoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepo extends ArangoRepository<UserEntity, String> {

//  <T> Optional<UserEntity> findOne(Example<T> of, ExampleMatcher withIgnorePaths);

  Optional<UserEntity> findByEmailAndPassword(String email, String password);
  Optional<UserEntity> findByEmailOrName(String email, String name);

//  @Query("FOR u in users FILTER u.email == @email AND u.password == @password")
//  Optional<UserEntity>findBy(@Param("email") String  email, @Param("password") String password);

  UserEntity findByEmail(String email);
  boolean existsUserEntityByName(String name);
  boolean existsUserEntityByEmail(String email);

}
