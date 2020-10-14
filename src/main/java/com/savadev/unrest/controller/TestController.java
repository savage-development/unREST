package com.savadev.unrest.controller;

import com.savadev.unrest.dao.file.JwkRepository;
import com.savadev.unrest.service.user.UserService;
import org.reactivestreams.Publisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    private final UserService userService;

    private final JwkRepository jwkRepository;

    public TestController(UserService userService, JwkRepository jwkRepository) {
        this.userService = userService;
        this.jwkRepository = jwkRepository;
    }

    @GetMapping("/user/{name}")
    Publisher<?> user(@PathVariable("name") String name) {
        return userService.findByUsername(name);
    }

}
