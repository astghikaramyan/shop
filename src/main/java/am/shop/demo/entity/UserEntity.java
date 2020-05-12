package am.shop.demo.entity;

import com.arangodb.springframework.annotation.Document;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Data
@Document("user")
public class UserEntity implements Serializable {
  @Id
  private String id;

  @NotNull
  private String name;

  private String email;

  private String password;

  private Set<String> roles;

}
