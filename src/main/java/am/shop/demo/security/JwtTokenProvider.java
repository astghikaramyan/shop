package am.shop.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
public class JwtTokenProvider implements Serializable {

  @Value("${app.jwtSecret}")
  private String jwtSecret;

  @Value("${app.jwtExpirationInMs}")
  private int jwtExpirationInMs;

//  private Clock clock = DefaultClock.INSTANCE;
//  public String getUserEmailFromToken(String token){
//    return getClaimsFromToken(token, Claims::getSubject);
//  }
//
//  public Date getExpirationTimeFromToken(String token){
//    return getClaimsFromToken(token, Claims::getExpiration);
//  }
//  public Date getIssuedAtDateFromToken(String token){
//    return getClaimsFromToken(token, Claims::getIssuedAt);
//  }
//  public <T> T getClaimsFromToken(String token, Function<Claims, T> claimsResolver){
//    final Claims claims = getAllClaimsFromToken(token);
//    return claimsResolver.apply(claims);
//  }
//  public Claims getAllClaimsFromToken(String token){
//    return Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(token).getBody();
//  }
//  private Boolean isTokenExpired(String token){
//    Date expiration = getExpirationTimeFromToken(token);
//    return expiration.before(clock.now());
//  }
//  private Boolean ignoreTokenExpiration(String token){
//    return false;
//  }

  public String generateToken(Authentication authentication){

    UserPrincipal userPrincipal = (UserPrincipal)authentication.getPrincipal();

    Date now = new Date();

    Date expirationdate = new Date(now.getTime() + this.jwtExpirationInMs);

    return Jwts
      .builder()
      .setSubject(userPrincipal.getEmail())
      .setIssuedAt(new Date())
      .setExpiration(expirationdate)
      .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
  }

  public String getUserEmailFromJwt(String token){
    Claims claims = Jwts.parser()
      .setSigningKey(jwtSecret)
      .parseClaimsJws(token)
      .getBody();
    return claims.getSubject();
  }

  public String getUserIdFromToken(String token){
    Claims claims = Jwts.parser()
      .setSigningKey(jwtSecret)
      .parseClaimsJws(token)
      .getBody();
    return claims.getSubject();
  }
  public boolean validateToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

//  public Boolean canTokenBeRefreshed(String token){
//    return (!isTokenExpired(token) || ignoreTokenExpiration(token));
//  }
//  private Date calculateExpirationDate(Date createdDate){
//    return new Date(createdDate.getTime() + ACCESS_TOKEN_VALIDITY_SECONDS * 30);
//  }
//  public String refreshToken(String token){
//    final Date createdDate = clock.now();
//    final Date expirationDate = calculateExpirationDate(createdDate);
//    final Claims claims = getAllClaimsFromToken(token);
//    claims.setIssuedAt(createdDate);
//    claims.setExpiration(expirationDate);
//    return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, SIGNING_KEY).compact();
//  }
//  public UserEntity validate(String token){
//    UserEntity user = null;
//    try{
//      Claims claims = Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(token).getBody();
//      user = new UserEntity();
//      user.setEmail(claims.getSubject());
//      user.setId(claims.getId());
//    }catch (Exception e){
//      e.printStackTrace();
//    }
//    return user;
//  }



//  public String getUserIdFromJwt(String token){
//    Claims claims = Jwts.parser()
//      .setSigningKey(jwtSecret)
//      .parseClaimsJws(token)
//      .getBody();
//    return claims.getSubject();
//  }


}


//https://github.com/pinalp887/JwtAuthenticationWithRoleBase/blob/master/src/main/java/com/cignex/jwt/JwtTokenUtil.java
//https://medium.com/@hantsy/protect-rest-apis-with-spring-security-and-jwt-5fbc90305cc5
//https://www.youtube.com/watch?v=R0-eoLp871s
