package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FileController {
    @Autowired
    private FileService fileService;
    @PostMapping("/files")
    public String saveFile(Authentication authentication, MultipartFile fileUpload) throws IOException {
        //Users user = (Users) authentication.getPrincipal();
        //System.out.println(user.getUserName());
        if (fileUpload.isEmpty()) {
            return "redirect:/result?error";
        }
        fileService.addFile(fileUpload, 1);
        return "redirect:/result?success";
    }
    @GetMapping("/files/delete")
    public String deleteFile(@RequestParam int fileId){
        if(fileService.deleteFile(fileId)>0){
            return "redirect:/result?success";
        }
        return "redirect:/result?error";
    }
}