package am.shop.demo.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@CrossOrigin
@WebFilter(urlPatterns = "/api/**")
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  @Autowired
  private CustomUserDetailsService customUserDetailsService;
  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {


      try {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "HEAD, POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With, X-Auth-Token");


//        response.addHeader("Access-Control-Allow-Origin", "*");
//        response.addHeader("Access-Control-Allow-Methods","GET, OPTIONS, HEAD, PUT, POST");
//        response.setHeader("Access-Control-Allow-Headers", "Authorization, X-Auth-Token, Content-Type");

        // For HTTP OPTIONS verb/method reply with ACCEPTED status code -- per CORS handshake
        if (request.getMethod().equals("OPTIONS")) {
          response.setStatus(HttpServletResponse.SC_ACCEPTED);
          return;
        }
        String jwt = this.getJwtFromRequest(request);
        if(StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
           /*
                    Note that you could also encode the user's username and roles inside JWT claims
                    and create the UserDetails object by parsing those claims from the JWT.
                    That would avoid the following database hit. It's completely up to you.
                 */
          String email = jwtTokenProvider.getUserEmailFromJwt(jwt);
          UserDetails userDetails = this.customUserDetailsService.loadUserByEmail(email);
          UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            userDetails, null,userDetails.getAuthorities());
          authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

          SecurityContextHolder.getContext().setAuthentication(authentication);
        }
      } catch (IllegalArgumentException e) {
        //loggeri mech grel
//        logger.error("an error occured during getting username from token", e);
        e.printStackTrace();
      } catch (ExpiredJwtException e) {
//        logger.warn("the token is expired and not valid anymore", e);
        e.getMessage();

      } catch (SignatureException e) {
//        logger.error("Authentication Failed. Username or Password not valid.");
        e.getMessage();
      }
      filterChain.doFilter(request, response);
    }


  private String getJwtFromRequest(HttpServletRequest request){
    String bearerToken = request.getHeader("Authorization");
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7, bearerToken.length());
    }
    return null;
  }

}
//https://www.devglan.com/spring-security/spring-boot-jwt-auth
//https://www.toptal.com/java/rest-security-with-jwt-spring-security-and-java
//https://www.youtube.com/watch?v=R0-eoLp871s
//https://medium.com/@hantsy/protect-rest-apis-with-spring-security-and-jwt-5fbc90305cc5
//https://github.com/hantsy/springboot-jwt-sample
