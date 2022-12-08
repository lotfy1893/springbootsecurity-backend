package com.security.springbasicsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanController {


  @GetMapping("/myloans")
  public String getLoans(){
    return "my loans";
  }
}
