package am.shop.demo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class OrderDto {
  private String id;

  private String productId;

  private String userId;

  private String done;

  private String orderTime;

  private String quantity;

  private String cardNumber;

  private String cardId;
}
