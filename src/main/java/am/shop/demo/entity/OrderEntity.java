package am.shop.demo.entity;

import com.arangodb.springframework.annotation.Document;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Document("orders")
public class OrderEntity implements Serializable {

  @Id
  private String id;

  private String productId;

  private String userId;

  private String done;

  private String quantity;

  @NotNull
  private String cardNumber;

  @NotNull
  private String cardId;

  @NotNull
  private String orderTime;

}
