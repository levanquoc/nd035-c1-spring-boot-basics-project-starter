package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CredentialsService {
    @Autowired
    private CredentialsMapper credentialsMapper;
    @Autowired
    private EncryptionService encryptionService;


    private Credentials encryptPassword(Credentials credential) {
        String key = RandomStringUtils.random(16, true, true);
        credential.setKey(key);
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), key));
        return credential;
    }

    public Credentials decryptPassword(Credentials credential) {
        credential.setPassword(encryptionService.decryptValue(credential.getPassword(), credential.getKey()));
        return credential;
    }

    public int addCredentials(Credentials credentials, int userid) {

        return credentialsMapper.addCredentials(encryptPassword(credentials),userid);
    }

    public List<Credentials> getAllCredentials(int userid) throws Exception {
        List<Credentials> credentials = credentialsMapper.getAllCredentials(userid);
        if (credentials == null) {
            throw new Exception();
        }
        return credentials.stream().map(this::decryptPassword).collect(Collectors.toList());
    }

    public int deleteCredentials(int credentialid) {
        return credentialsMapper.deleteCredentials(credentialid);
    }

    public int updateCredentials(Credentials credentials) {
        return credentialsMapper.updateCredentials(this.encryptPassword(credentials));
    }
}
