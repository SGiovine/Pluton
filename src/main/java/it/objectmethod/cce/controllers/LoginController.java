package it.objectmethod.cce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.objectmethod.cce.dto.UserDTO;
import it.objectmethod.cce.services.UserService;

@Controller
public class LoginController {

	@Autowired
	UserService userServ;
	
	@PostMapping("/login")
	public @ResponseBody ResponseEntity<UserDTO> loginPost(@RequestParam("email") String emailToCheck,
			@RequestParam("password") String passwordToCheck) {

		UserDTO userDTO = userServ.loginCheck(emailToCheck, passwordToCheck);
		
		if (userDTO != null) {
			return new ResponseEntity<>(userDTO,HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
	}

}
