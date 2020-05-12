package am.shop.demo.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException {
  private String resourceName;
  private String fieldName;
  private Object fieldValue;

  public ResourceNotFound(String resourceName, String fieldName, Object fieldValue){
    super(String.format("%s not found with %s : '%s' value", resourceName, fieldName, fieldValue));
    this.resourceName = resourceName;
    this.fieldName = fieldName;
    this.fieldValue = fieldValue;
  }

}
