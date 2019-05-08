package com.busra.notes;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.busra.entity.Note;
import com.busra.service.MailService;
import com.busra.service.NoteService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	public static String url = "http://localhost:8080/notes";
	
	@Autowired
	private NoteService noteService;
	
	@Autowired
	private MailService mailService;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("title", "Blog Posts" );
		model.addAttribute("notes" , noteService.getAll(1L));

		return "home";
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String homee(Locale locale, Model model) {
		
		
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homes(Locale locale, Model model) {
	
		return "redirect:/home";
	}
	@RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id , Model model) {

		System.out.println(id);
		
		model.addAttribute("id" , id);
		
		mailService.registerMail("bbusrayildizz34@gmail.com", "123");
		
//		for(Note note : noteService.getAll())
//			System.out.println(note.getContent());
		
		return "detail";
	}
	
	@RequestMapping(value = "addNote", method = RequestMethod.GET)
	public String addNote(Locale locale, Model model) {

		for(Note note : noteService.getAll())
			System.out.println(note.getContent());
		
		return "addNote";
	}
	
	@RequestMapping(value="/addNote" , method=RequestMethod.POST)
	public ResponseEntity<String> addNote(@RequestBody Note note , HttpServletRequest request){
		
		System.out.println(note.toString());
		noteService.createNote(note, request);
		
		return new ResponseEntity<String>("OK",HttpStatus.OK);		
	}
	
	@RequestMapping(value="/updateNote" , method=RequestMethod.POST)
	public ResponseEntity<String> updateNote(@RequestBody Note note , HttpServletRequest request){
		
		Note oldNote = noteService.getNoteById(note.getId());
		oldNote.setTitle(note.getTitle());
		oldNote.setContent(note.getContent());
		
		noteService.updateNote(note, request);
		
		return new ResponseEntity<String>("OK",HttpStatus.OK);		
	}
	
	
	
	
	@RequestMapping(value="/deleteNote" , method=RequestMethod.POST)
	public ResponseEntity<String> deleteNote(@RequestBody Note note , HttpServletRequest request){
		
	
		Note oldNote = noteService.getNoteById(note.getId());
				
		noteService.deleteNote(note, request);
		
		return new ResponseEntity<String>("OK",HttpStatus.OK);		
	}
	
	
	@RequestMapping(value="/getNotes" , method=RequestMethod.POST)
	public ResponseEntity<ArrayList<Note>> getNotes( HttpServletRequest request){
				
		return new ResponseEntity<ArrayList<Note>>(noteService.getAll(),HttpStatus.OK);		
	}
	
	@RequestMapping(value="/getNote" , method=RequestMethod.POST)
	public ResponseEntity<Note> getNote(@RequestBody String id , HttpServletRequest request){
				
		return new ResponseEntity<Note>(noteService.getNoteById(Long.parseLong(id)),HttpStatus.OK);		
	}
	
}
