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

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailsService userDetailsService;

    /**
     * Set up a password encoder.
     */
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        AuthenticationSuccessHandler successHandler = new
                SavedRequestAwareAuthenticationSuccessHandler();
        http
                .authorizeRequests()
                .antMatchers("/admin/**")
                .access("hasRole('ROLE_2')")
                .antMatchers("/manager/**")
                .access("hasRole('ROLE_1')")
                .antMatchers("/TimeSheetForm/**")
                .access("hasRole('ROLE_0')")
                .antMatchers("/user/**")
                .access("hasAnyRole('ROLE_0','ROLE_1', 'ROLE_2')" )
                .antMatchers("/TimeSheetDetails/**")
                .access("hasRole('ROLE_0')")
                .antMatchers("/h2_console/**").permitAll()
                .antMatchers("/").permitAll()
                .and()
                .formLogin()
                .successHandler(successHandler)
                .loginPage("/login")
                .defaultSuccessUrl("/loginSuccess")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403")
                .and()
                .csrf().disable();

        http.headers().frameOptions().disable();
    }

    /**
     * Point the authentication to our own user service.
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new
                DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
}
