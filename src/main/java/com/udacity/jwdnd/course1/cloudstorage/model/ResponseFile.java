package com.udacity.jwdnd.course1.cloudstorage.model;
import lombok.*;
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public class ResponseFile {
        private int fileid;
        private String filename;
        private String dataURL;

    }
