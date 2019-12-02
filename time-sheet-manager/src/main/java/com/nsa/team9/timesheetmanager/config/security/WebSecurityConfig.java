package com.nsa.team9.timesheetmanager.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;


/**
 * https://spring.io/guides/gs/securing-web/
 */


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private MyUserDetailsService userDetailsService;

  /**
   * Set up a password encoder
   */

  @Bean
  public static PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

//  @Bean
//  @Override
//  public UserDetailsService userDetailsService() {
//    UserDetails user =
//            User.withDefaultPasswordEncoder()
//                    .username("user")
//                    .password("password")
//                    .roles("USER")
//                    .build();
//
//    return new InMemoryUserDetailsManager(user);
//  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    AuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();


    http
            .authorizeRequests()
            .antMatchers("/admin/**").access("hasRole('2')")
            .antMatchers("/manager/**").access("hasRole('1')")
            .antMatchers("/h2_console/**").permitAll()
            .antMatchers("/").permitAll()
            //.anyRequest().authenticated()
            .and()
            .formLogin()
            .successHandler(successHandler)

            .loginPage("/login")

            .permitAll()
            .and()
            .logout()
            .logoutSuccessUrl("/login")
            .permitAll()
            .and()
            .exceptionHandling().accessDeniedPage("/403")
            .and()
            .csrf()
            .ignoringAntMatchers("/h2-console/**")
    ;

    http.headers().frameOptions().disable();

  }

  /**
   * Point the authentication to our own user service
   */

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider());
  }


}
