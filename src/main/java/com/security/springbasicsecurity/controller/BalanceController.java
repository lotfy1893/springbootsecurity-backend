package com.security.springbasicsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {

  @GetMapping("/mybalance")
  public String getBalance(){
    return "Here are the balance from db";
  }

}
