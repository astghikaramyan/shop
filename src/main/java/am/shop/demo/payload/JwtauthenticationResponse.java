package am.shop.demo.payload;

import lombok.Data;

@Data
public class JwtauthenticationResponse {
  private String accessToken;
  private String tokenType = "Bearer";

  public JwtauthenticationResponse(String accessToken){
    this.accessToken = accessToken;
  }
}
