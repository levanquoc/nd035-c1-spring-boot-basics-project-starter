package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller

public class HomeController {
    @Autowired
    private UserService userService;
    @Autowired
    private FileService fileService;
    @Autowired
    private NotesService notesService;
    @Autowired
    private CredentialsService credentialsService;
    @GetMapping({"/home","/"})
    public  ModelAndView homPage(Authentication authentication) throws Exception {
        String userLogin=  authentication.getPrincipal().toString();
        Users users = userService.getUser(userLogin);
        ModelAndView modelAndView=new ModelAndView("home");
        //List<Users> listUsers= userService.findAll();
        modelAndView.addObject("files", fileService.findAllFiles(users.getUserId()));
        modelAndView.addObject("notes",notesService.getAllNotes(users.getUserId()));
        modelAndView.addObject("credentials",credentialsService.getAllCredentials(users.getUserId()));
        return modelAndView;
    }

}
