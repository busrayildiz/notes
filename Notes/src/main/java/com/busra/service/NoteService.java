package com.busra.service;

import java.util.ArrayList;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.busra.dao.NoteDAO;
import com.busra.entity.Note;

@Service
@Transactional
public class NoteService {

	@Autowired
	private NoteDAO noteDAO;

	public Long createNote(Note note , HttpServletRequest request) {
		// TO DO : user id de�i�ecek
		note.setUserId(1L);
		return noteDAO.insert(note);
	}
	
	public Long updateNote(Note note , HttpServletRequest request) {
		
		noteDAO.update(note);
		return 1l;
	}
	
	public Long deleteNote(Note note , HttpServletRequest request) {
		noteDAO.delete(note);
		return 1l;
	}

	public ArrayList<Note> getAll(){

		return  noteDAO.getAll() ;
	}

	public ArrayList<Note> getAll(Long userId){

		return  noteDAO.getAll(userId);
	}
	
	public Note getNoteById(Long id){
		
		return noteDAO.getNoteById(id);
	}
	

}
