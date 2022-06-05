package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CredentialsController {
    @Autowired
    private CredentialsService credentialsService;
    @Autowired
    private UserService userService;
    @PostMapping("/credentials")
    public String updateOrAddCredentials(Authentication authentication, @ModelAttribute Credentials credentials){
        String userLogin=  authentication.getPrincipal().toString();
        Users users = userService.getUser(userLogin);
        System.out.println(credentials.getCredentialid());
        if(credentials.getCredentialid()==0) {
            if (credentialsService.addCredentials(credentials, users.getUserId()) > 0) {
                return "redirect:/result?success";
            }
        }
        if(credentialsService.updateCredentials(credentials)>0){
            return "redirect:/result?success";
        }

        return "redirect:/result?error";
    }
    @GetMapping("credentials/delete")
    public String deleteCredentials(@RequestParam("id") int credentialid){
        if(credentialsService.deleteCredentials(credentialid)>0){
            return "redirect:/result?success";
        }
        return "redirect:/result?error";
    }
}
