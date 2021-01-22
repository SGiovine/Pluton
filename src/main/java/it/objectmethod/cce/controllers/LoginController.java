package it.objectmethod.cce.controllers;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import it.objectmethod.cce.dto.UserDTO;
import it.objectmethod.cce.services.TokensService;
import it.objectmethod.cce.services.UserService;

@Controller
public class LoginController {

	private static final Logger logger = LogManager.getLogger(LoginController.class);

	@Autowired
	UserService userServ;

	@Autowired
	TokensService tokenServ;

	@PostMapping("/login")
	public @ResponseBody ResponseEntity<String> loginPost(@RequestParam("email") String emailToCheck,
			@RequestParam("password") String passwordToCheck) {

		UserDTO userDTO = userServ.loginCheck(emailToCheck, passwordToCheck);

		if (userDTO != null) {

			String token = tokenServ.generateToken(userDTO);
			logger.info("logincontroller,login,token: " + token);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("token", token);

			return new ResponseEntity<String>("", responseHeaders, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

	}

	@PostMapping("/dummy")
	public ResponseEntity<String> dummy() {

		return new ResponseEntity<String>("ciao, dummy", HttpStatus.OK);

	}

}
