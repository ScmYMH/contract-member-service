package com.scm.contract.user.controller;

import com.scm.contract.user.dto.UserDto;
import com.scm.contract.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Stream;

@RestController
@RequestMapping("contract/user")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public Stream<UserDto> showUser() {

        return userService.getUser();
    }

    @GetMapping("/id")
    public Stream<UserDto> showUserById(@RequestParam String loginId) {

        return userService.getUserById(loginId);
    }

    @GetMapping("/name")
    public Stream<UserDto> showUserByName(@RequestParam String userNm) {

        return userService.getUserByUserNm(userNm);
    }



}
