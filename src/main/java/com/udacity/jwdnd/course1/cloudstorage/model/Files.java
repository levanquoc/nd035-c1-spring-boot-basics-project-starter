package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Files {
    private int fileId;
    private  String filename;
    private String contenttype;
    private String filesize;
    private byte[] filedata;
    private Users users;
}
