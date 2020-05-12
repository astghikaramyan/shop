package am.shop.demo.payload;

import lombok.Data;

@Data
public class ApiResponse {
  private boolean success;
  private String message;
  public ApiResponse(boolean sucess, String message){
    this.success = sucess;
    this.message = message;
  }
}
