package am.shop.demo.config;


import am.shop.demo.security.CustomUserDetailsService;
import am.shop.demo.security.JwtAuthenticationEntryPoint;
import am.shop.demo.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
  securedEnabled = true,
  jsr250Enabled = true,
  prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  CustomUserDetailsService customUserDetailsService;

  @Autowired
  private JwtAuthenticationEntryPoint unauthorizedHandler;

  @Bean
  public JwtAuthenticationFilter jwtAuthenticationFilter(){
    return new JwtAuthenticationFilter();
  }

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Bean(BeanIds.AUTHENTICATION_MANAGER)
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }


  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .cors()
          .and()
      .csrf()
          .disable()
//      .exceptionHandling()
//        .authenticationEntryPoint(unauthorizedHandler)
//        .and()
//      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        .and()
      .authorizeRequests()
        .antMatchers(
          "/",
          "/favicon.ico",
          "/**/*.png",
          "/**/*.gif",
          "/**/*.svg",
          "/**/*.jpg",
          "/**/*.html",
          "/**/*.css",
          "/**/*.js"
        )
        .permitAll()
      .antMatchers("/api/users/auth/signin","/api/users/auth/signup")
        .permitAll()
      .anyRequest()
        .authenticated()
      .and().addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
  }


//    private JwtTokenUtil jwtTokenUtil;
//    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//    private UserServiceImpl userService;
//
//    @Autowired
//    public SecurityConfig(JwtTokenUtil jwtTokenUtil, UserServiceImpl userService, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint){
//      this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
//      this.jwtTokenUtil = jwtTokenUtil;
//      this.userService = userService;
//    }
//
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//      return super.authenticationManagerBean();
//    }
//
//
////  @Bean
////  public JwtAuthenticationFilter authenticationFilterBean(){
////    return new JwtAuthenticationFilter(jwtTokenUtil);
////  }
//
//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//    http
//      .httpBasic().disable()
//      .csrf().disable()
//      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//      .and()
//      .authorizeRequests()
//      .antMatchers(HttpMethod.POST, "/api/users/token/*").permitAll()
//      .anyRequest().authenticated()
//      .and()
//      .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
//      .and()
//      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//      .and()
//      .apply(new JwtConfigurer(jwtTokenUtil));
////    http.addFilterBefore(authenticationFilterBean(), UsernamePasswordAuthenticationFilter.class);
//  }
}
