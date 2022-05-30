package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    private int userId;
    private String userName;
    private String salt;
    private String passWord;
    private String firstName;
    private String lastName;




}
