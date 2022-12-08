package com.security.springbasicsecurity.controller;

import com.security.springbasicsecurity.model.Customer;
import com.security.springbasicsecurity.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @PostMapping("/register")
  public ResponseEntity<String> registerUser(@RequestBody Customer customer){
    Customer customerSaved = null;
    ResponseEntity response = null;

    try{
      String hashPassword = passwordEncoder.encode(customer.getPwd());
      customer.setPwd(hashPassword);
      customerSaved = customerRepository.save(customer);
      if (customerSaved.getId() > 0){
        response = ResponseEntity.status(HttpStatus.CREATED).body("Customer " + customerSaved.getId() + " saved sucessfully!");
      }
    }catch (Exception exception){
      response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error happened during registration! -- "+ exception.getMessage());
    }
    return  response;
  }
}
