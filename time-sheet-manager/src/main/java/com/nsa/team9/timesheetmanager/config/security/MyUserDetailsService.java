package com.nsa.team9.timesheetmanager.config.security;


import com.nsa.team9.timesheetmanager.domain.Login;
import com.nsa.team9.timesheetmanager.services.LoginSearchImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

  @Autowired
  private LoginSearchImpl loginSearch;
//  @Autowired
//  private UserRolesRepository userRolesRepository;
  @Autowired
  private PasswordEncoder encoder;

  @Override
  public UserDetails loadUserByUsername(String email) {

    System.out.println("password encoded = " + encoder.encode("password"));

    System.out.println("Email: " + email);
    Login user = loginSearch.getLoginByEmail(email).get();
    if (user == null) {
      throw new UsernameNotFoundException(email);
    } else {
      System.out.println("User = " + user);

      List<String> userRoles = new ArrayList<>();
      userRoles.add("ROLE_"+user.accessLevel.toString());
      System.out.println(userRoles);
      return new MyUserPrincipal(user, userRoles);
    }
  }

//  public Login registerNewUserAccount(LoginForm loginForm) {
//
//    Login newUser = new Login(null, loginForm.getEmail(), loginForm.getPassword(), null);
//
//
//    return newUser;

//  }

}
