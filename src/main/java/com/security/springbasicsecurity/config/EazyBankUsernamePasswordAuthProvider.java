package com.security.springbasicsecurity.config;

import com.security.springbasicsecurity.model.Customer;
import com.security.springbasicsecurity.repository.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EazyBankUsernamePasswordAuthProvider implements AuthenticationProvider {
  @Autowired
  private CustomerRepository customerRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName();
    String pwd = authentication.getCredentials().toString();

    List<Customer> customers = customerRepository.findByEmail(username);

    if (customers != null && !customers.isEmpty()) {

      if (passwordEncoder.matches(pwd, customers.get(0).getPwd())) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(customers.get(0).getRole()));
        return new UsernamePasswordAuthenticationToken(username, pwd, authorities);
      } else {
        throw new BadCredentialsException("invalid credentials");
      }

    } else {
      throw new BadCredentialsException("Not a user");
    }
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
  }
}
