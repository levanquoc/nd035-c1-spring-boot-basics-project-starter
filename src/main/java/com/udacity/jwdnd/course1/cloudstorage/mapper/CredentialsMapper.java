package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository

public interface CredentialsMapper {
    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES (#{credentials.url}, #{credentials.username}, #{credentials.key}, #{credentials.password}, #{userid})")
   public int addCredentials(Credentials credentials, int userid);

    @Select("SELECT * FROM CREDENTIALS WHERE USERID=#{userid}")
    public List<Credentials> getAllCredentials(int userid);

    @Delete("DELETE CREDENTIALS WHERE credentialid=#{credentialid}")
    public int deleteCredentials(int credentialid);
    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, key = #{key}, password = #{password} WHERE credentialid = #{credentialid}")
    int updateCredentials(Credentials credentials);
}
