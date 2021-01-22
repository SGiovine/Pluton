package it.objectmethod.cce.services;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

import javax.crypto.SecretKey;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import it.objectmethod.cce.controllers.LoginController;
import it.objectmethod.cce.dto.UserDTO;
import it.objectmethod.cce.entities.User;
import it.objectmethod.cce.repository.UserRepository;

@Service
public class TokensService {

	private static final Logger logger = LogManager.getLogger(LoginController.class);
	
	@Autowired
	UserRepository userRep;

	final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

	public String generateToken(UserDTO userdto) {

		final long TOKEN_EXPIRY_DURATION = 20;
		Gson gson = new Gson();
		String user = gson.toJson(userdto, UserDTO.class);
		return Jwts.builder().setSubject(user)
				.setExpiration(Date.from(ZonedDateTime.now().plusSeconds(TOKEN_EXPIRY_DURATION).toInstant()))
				.signWith(SECRET_KEY).compact();
	}

	public boolean tokenValidator(String token) {

		boolean userAuthentication = false;
		
		String tokenUserDTO = null;
		
		try {
			Claims claims = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
			tokenUserDTO = claims.getSubject();
		} catch (ExpiredJwtException e) {
			logger.error("TokenService,tokenvalidator, token is expired");
		} catch(SignatureException e) {
			logger.error("TokenService,tokenvalidato, token signature doesn't match");
		}
		
		Gson gson = new Gson();
		UserDTO userDTOparsed = gson.fromJson(tokenUserDTO, UserDTO.class);
		
		if(userDTOparsed != null) {
		Optional<User> user = userRep.findById(userDTOparsed.getIdUtente());
			
		userAuthentication = user.get().getEmail().equals(userDTOparsed.getEmail()) && user.get().getRole().equals(userDTOparsed.getRole());
		logger.info("tokenservice,tokenvalidator,userAuthentication: "+userAuthentication);
		}
		
		return userAuthentication;
	}

}
