package com.security.springbasicsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardsController {

  @GetMapping("/mycards")
  public String getCards(){
    return "Here are the cards from db";
  }
}
