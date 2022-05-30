package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FileMapper {

    @Select("SELECT * FROM FILES WHERE fileId =#{fileId")
    public Files findOneFile(int fileId);

    @Select("SELECT * FROM FILES WHERE userId =#{userId}")
    public List<Files> findAllFiles(int userId);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, filedata, userid) VALUES (#{file.filename}, #{file.contenttype}, #{file.filesize}, #{file.filedata}, #{userid})")
    public int insertFile(Files file, int userid);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    public int deleteFile(int fileId);

}
