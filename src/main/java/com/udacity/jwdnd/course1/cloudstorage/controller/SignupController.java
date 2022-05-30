package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class SignupController {
    @Autowired
    private UserService userService;
    @GetMapping("/signup")
    public ModelAndView register(){
        ModelAndView modelAndView =new ModelAndView("signup");
        return modelAndView;
    }
    @PostMapping("/signup")
    public String register(@ModelAttribute Users users){
        try {
            userService.insertUser(users);
        }catch (Exception e){
            return  "redirect:/signup?error";
        }
        return "redirect:/signup?success";
    }
}
