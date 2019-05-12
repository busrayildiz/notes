package com.busra.notes;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.busra.entity.User;
import com.busra.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam(value="status",required=false) String status,Model model) {

		if(status!=null) {
			System.out.println(status);
			if(status.equals("ok"))
				model.addAttribute("status", "Succesfully completed");
			else
				model.addAttribute("status", "Error ! Try Again");
		}
		return "login";
	}

	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {

		return "register";
	}
	
	@RequestMapping(value = "reg/{key}", method = RequestMethod.GET)
	public String regOk(@PathVariable("key") String key,Model model) {
		
		if(userService.getFindByKey(key)) {
			return "redirect:/login?status=ok";
		}
		return "redirect:/login?status=error";
	}
	
	
	@RequestMapping(value = "/controlUser", method = RequestMethod.POST)
	public ResponseEntity<String> controlUser(@RequestBody User user , HttpServletRequest request) {
	
		
			if(userService.getFindByUnameAndPass(user) != null) {
				return new ResponseEntity<String>("OK",HttpStatus.OK);
			}
		    
			return new ResponseEntity<String>("ERROR",HttpStatus.OK);
		
	}
	
	
	
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ResponseEntity<String> addUser(@RequestBody User user , HttpServletRequest request) {
		
		int status = control(user);
		
		if(status != 0) {
			return new ResponseEntity<String>(status+"",HttpStatus.OK);
		}
		
			if(userService.insert(user).equals(1l)) {
				return new ResponseEntity<String>("OK",HttpStatus.CREATED);
			}
		    
			return new ResponseEntity<String>("ERROR",HttpStatus.CREATED);
		
	}
	
	private int control (User user) {
		if(user.getPass().equals(user.getPass2())) {
			return 0; 
		
		}else
		return 1 ;
	}
}
