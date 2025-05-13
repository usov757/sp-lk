package ru.bgpu.splk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bgpu.splk.models.User;
import ru.bgpu.splk.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired private UserService userService;

    @GetMapping
    public List<User> index() {
        return userService.listUsers();
    }

    @GetMapping("/{name}/test")
    public User getByName(@PathVariable String name) {
        return userService.getByName(name);
    }
}
