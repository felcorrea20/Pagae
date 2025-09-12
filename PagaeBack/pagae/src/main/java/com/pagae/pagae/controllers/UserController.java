package com.pagae.pagae.controllers;

import com.pagae.pagae.models.UserModel;
import com.pagae.pagae.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/usuario")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/cadastro")
    public UserModel createUser (@RequestBody UserModel userModel) {
        return userService.createUser(userModel);
    }

    @GetMapping(path = "/listar")
    public List<UserModel> findAll () {
        return userService.findAll();
    }

    @DeleteMapping(path = "/deletar")
    public void deleteUser (@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
