package com.victor.security.config;

import com.victor.security.filter.JwtAuthenticationTokenFilter;
import com.victor.security.handler.CustomAuthenticationFailureHandler;
import javax.annotation.Resource;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @Description:
 * @Param:
 * @return:
 * @Author: fhz
 * @Date: 09:32 2018-12-19
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Resource
  private UserDetailsService userDetailsService;

  @Resource
  private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

  @Resource
  private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

  private static final String[] AUTH_WHITE_LIST = {
      "/swagger-ui.html",
      "/webjars/**",
      "/swagger-resources",
      "/swagger-resources/**",
      "/v2/api-docs",
      "/auth/**",
      "/static/**"
  };

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Resource
  public void configureAuthentication(AuthenticationManagerBuilder builder) throws Exception {
    builder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers(AUTH_WHITE_LIST);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
//    http.formLogin().loginPage("/auth/login").loginProcessingUrl("/auth/login")
//        .usernameParameter("username").passwordParameter("password")
//        .failureHandler(customAuthenticationFailureHandler)
//        .and().authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//        .antMatchers("/auth/**").permitAll().anyRequest().authenticated()
//        .and()
//        //.addFilter(new JwtLoginFilter(authenticationManager()))
//        .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
//        .csrf().disable().sessionManagement()
//        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        .and().headers().cacheControl();

    http.csrf().disable().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
        .and().authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
        .antMatchers("/auth/**").permitAll().anyRequest().authenticated()
        .and().headers().cacheControl();
    http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

    // 这块是配置跨域请求的
    ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http
        .authorizeRequests();
    //让Spring security放行所有preflight request
    registry.requestMatchers(CorsUtils::isPreFlightRequest).permitAll();
  }

  @Bean
  public CorsFilter corsFilter() {
    final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
    final CorsConfiguration cors = new CorsConfiguration();
    cors.setAllowCredentials(true);
    cors.addAllowedOrigin("*");
    cors.addAllowedHeader("*");
    cors.addAllowedMethod("*");
    urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", cors);
    return new CorsFilter(urlBasedCorsConfigurationSource);
  }

}
