package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.model.ResponseFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService {
    @Autowired
    private FileMapper fileMapper;

    public ResponseFile getResponseFile(Files file) {
        String base64 = Base64.getEncoder().encodeToString(file.getFiledata());
        String dataURL = "data:" + file.getContenttype() + ";base64," + base64;
        return ResponseFile.builder().fileid(file.getFileId()).filename(file.getFilename()).dataURL(dataURL).build();
    }
    public int addFile(MultipartFile fileUpload, int userid) throws IOException {
        Files file = new Files();
        try {
            file.setContenttype(fileUpload.getContentType());
            file.setFiledata(fileUpload.getBytes());
            file.setFilename(fileUpload.getOriginalFilename());
            file.setFilesize(Long.toString(fileUpload.getSize()));
        } catch (IOException e) {
            throw e;
        }
        return fileMapper.insertFile(file, userid);
    }

    public List<ResponseFile> findAllFiles(int userid) throws Exception {
        List<Files> files = fileMapper.findAllFiles(userid);
        if (files == null) {
            throw new Exception();
        }
        return files.stream().map(this::getResponseFile).collect(Collectors.toList());
    }
    public int deleteFile(int fileId){
        return fileMapper.deleteFile(fileId);
    }
}

