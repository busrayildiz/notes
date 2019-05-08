package com.busra.dao;

import java.util.ArrayList;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.busra.entity.Note;

@Repository
public class NoteDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	//CRUD
	
	public Long insert(Note note) {
		
		return (Long) sessionFactory.getCurrentSession().save(note);			 
	}
	
	public void update(Note note) {
		sessionFactory.getCurrentSession().update(note);
	}

	public void persist(Note note) {  //id ye bak�p varsa update ediyor yoksa insert ediyor
		sessionFactory.getCurrentSession().persist(note);
	}
	
	public void delete(Note note) {
		sessionFactory.getCurrentSession().delete(note);
	}
	
	public Note getNoteById(Long id){
		Query query = sessionFactory.getCurrentSession().createQuery("FROM Note WHERE id=:id")
				.setLong("id", id);
		return (Note) query.getSingleResult();
	}
	
	public ArrayList<Note> getAll(){
		Query query = sessionFactory.getCurrentSession().createQuery("FROM Note");
		return (ArrayList<Note>) query.getResultList();
	}
	
	public ArrayList<Note> getAll(Long userId){
		Query query = sessionFactory.getCurrentSession().createQuery("FROM Note WHERE userId=:userId order by id desc")
				.setLong("userId", userId);
		return (ArrayList<Note>) query.getResultList();
	}
	
}
