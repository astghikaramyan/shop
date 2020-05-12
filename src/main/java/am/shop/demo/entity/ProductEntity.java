package am.shop.demo.entity;

import com.arangodb.springframework.annotation.Document;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Document("products")
@Data
public class ProductEntity implements Serializable {
  @Id
  private String id;

  @NotNull
  private String name;

  private String price;

  private String desc;

  private String catId;

  private String imgUrl;
}
