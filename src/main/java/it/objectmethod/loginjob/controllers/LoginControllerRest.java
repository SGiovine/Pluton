package it.objectmethod.loginjob.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import it.objectmethod.loginjob.dto.UserDTO;
import it.objectmethod.loginjob.services.UserService;

@Controller
public class LoginControllerRest {

	@Autowired
	UserService serv;

	@GetMapping("/login")
	public @ResponseBody ResponseEntity<String> error(@RequestParam("email") String emailToCheck,
			@RequestParam("password") String passwordToCheck) {

		UserDTO userDTO = serv.loginCheck(emailToCheck, passwordToCheck);
		Gson gson = new Gson();
		
		if (userDTO != null) {
			return new ResponseEntity<>(gson.toJson(userDTO),HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}

}
