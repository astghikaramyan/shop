package am.shop.demo.entity;

import com.arangodb.springframework.annotation.Document;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Document("categories")
public class CategoryEntity implements Serializable {
  @Id
  private String id;

  @NotNull
  private String name;

}
