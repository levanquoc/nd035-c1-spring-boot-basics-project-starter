package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notes {
    private int noteid;
    private String notetitle;
    private String notedescription;
    private Users users;
}
