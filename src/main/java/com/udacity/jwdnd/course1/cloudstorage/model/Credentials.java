package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Credentials {
    private int credentialid;
    private String url;
    private String username;
    private String key;
    private String password;
    private Users users;

}
