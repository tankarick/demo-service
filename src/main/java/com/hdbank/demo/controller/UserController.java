package com.hdbank.demo.controller;

import com.hdbank.demo.model.HdBankData;
import com.hdbank.demo.model.Response;
import com.hdbank.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("v1/customer")
    public ResponseEntity<Response> createUser(@RequestBody @Valid HdBankData data) {
        return ResponseEntity.ok(userService.getUserByName("234"));
    }
}
