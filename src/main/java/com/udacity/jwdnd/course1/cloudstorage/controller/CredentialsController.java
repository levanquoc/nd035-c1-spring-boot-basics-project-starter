package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CredentialsController {
    @Autowired
    private CredentialsService credentialsService;
    @PostMapping("/credentials")
    public String updateOrAddCredentials(@ModelAttribute Credentials credentials){
        if(credentials.getCredentialid()==0){
            if(credentialsService.addCredentials(credentials,1)>0){
                return "redirect:/result?success";
            }
        if(credentialsService.updateCredentials(credentials)>0){
            return "redirect:/result?success";
        }
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
