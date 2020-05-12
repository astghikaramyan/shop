package am.shop.demo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class ProductDto {

  private String id;

  @NotNull(message = "name can't be null or empty")
  private String name;

  private String price;

  private String desc;

  private String catId;

  private String imgUrl;
}
