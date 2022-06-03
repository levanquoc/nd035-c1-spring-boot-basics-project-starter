package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NotesController {
    @Autowired
    private NotesService notesService;
    @Autowired
    private UserService userService;
    @PostMapping("/notes")
    public String updateOrAddNotes(Authentication authentication, @ModelAttribute Notes notes){
        String userLogin=  authentication.getPrincipal().toString();
        Users users = userService.getUser(userLogin);
        if(notes.getNoteid()==0) {
            if (notesService.addNotes(notes, users.getUserId()) > 0) {
                return "redirect:/result?success";
            }
        }
        if(notesService.updateNotes(notes)>0){
            return "redirect:/result?success";
        }
        return "redirect:/result?error";
    }
    @GetMapping("/notes/delete")
    public String deleteNotes(@RequestParam("id") int noteid){
        if(notesService.deleteNotes(noteid)>0){
            return "redirect:/result?success";
        }
        return "redirect:/result?error";
    }
}
