package am.shop.demo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class UserDto {

  private String id;

  @NotNull(message = "name can't be null ")
  private String name;

  private String email;

  private String password;

  private Set<String> roles;
}
