package com.nfq.task.controller;

import com.nfq.task.domain.User;
import com.nfq.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    String signIn() {
        return "login";
    }

    @GetMapping("/register")
    String signUp(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    String signUp(User user) {

        userService.signUpUser(user);

        return "redirect:/login";
    }

}
