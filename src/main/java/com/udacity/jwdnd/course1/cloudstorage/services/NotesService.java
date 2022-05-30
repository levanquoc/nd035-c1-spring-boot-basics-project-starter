package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NotesService {
    @Autowired
    private NotesMapper notesMapper;
    public List<Notes> getAllNotes(int userid) {
      return notesMapper.getAllNotes(userid);
    }

    public int addNotes(Notes notes,int userid) {
        return notesMapper.addNotes(notes,userid);
    }

    public int deleteNotes(int noteid) {
        return notesMapper.deleteNotes(noteid);
    }

    public int updateNotes(Notes notes) {
        return notesMapper.updateNotes(notes);
    }
}
