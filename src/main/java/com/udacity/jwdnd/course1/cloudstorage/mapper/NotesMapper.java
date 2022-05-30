package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NotesMapper {
    @Select("SELECT * FROM NOTES WHERE USERID=#{userid}")
    public List<Notes> getAllNotes(int userid);

    @Insert("INSERT INTO NOTES(NOTETITLE,NOTEDESCRIPTION,USERID) VALUES(#{notes.notetitle},#{notes.notedescription},#{userid})")
    public int addNotes(Notes notes, int userid);

   @Delete("DELETE FROM NOTES WHERE NOTEID=#{noteid}")
    public int deleteNotes(int noteid);
    @Update("UPDATE NOTES SET NOTETITLE=#{notetitle},NOTEDESCRIPTION=#{notedescription} WHERE NOTEID=#{noteid}")
    public int updateNotes(Notes notes);
}
