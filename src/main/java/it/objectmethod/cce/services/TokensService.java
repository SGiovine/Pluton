package it.objectmethod.cce.services;

import java.time.ZonedDateTime;
import java.util.Date;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import it.objectmethod.cce.dto.UserDTO;
import it.objectmethod.cce.entities.User;
import it.objectmethod.cce.repository.UserRepository;

@Service
public class TokensService {

	@Autowired
	UserRepository userRep;


	final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

	public String generateToken(String user) {

		final long TOKEN_EXPIRY_DURATION = 20;

		return Jwts.builder().setSubject(user)
				.setExpiration(Date.from(ZonedDateTime.now().plusSeconds(TOKEN_EXPIRY_DURATION).toInstant()))
				.signWith(SECRET_KEY).compact();
	}

	private String decodeToken(String token) {

		Claims claims = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();

	/*	long instantNow = Date.from(ZonedDateTime.now().toInstant()).getTime();

		String userDTOtimeValid = "";

		if (instantNow < claims.getExpiration().getTime()) {
			userDTOtimeValid = claims.getSubject();
		}

		return userDTOtimeValid;*/
		return claims.getSubject();
	}

	public boolean tokenValidator(String token) {

		Gson gson = new Gson();
		String tokenUserDTO = decodeToken(token);
		UserDTO userDTOparsed = gson.fromJson(tokenUserDTO, UserDTO.class);

		gson = null;
		boolean userAuthentication = false;
		User user = userRep.findByIdutente(userDTOparsed.getIdUtente());

		UserDTO userDTO = null;

		if (user != null) {
			userDTO = new UserDTO();
			userDTO.setEmail(user.getEmail());
			userDTO.setIdUtente(user.getIdUtente());
			userDTO.setName(user.getName());
			userDTO.setRole(user.getRole());
		}
		System.out.println("tokenservices,tokenvalidator,tokenparsedtoobject equals repositoryuser: "+userDTOparsed.equals(userDTO));

		if (userDTO.equals(userDTOparsed)) {
			userAuthentication = true;
			System.out.println("tokenservices,tokenvalidator,userAuthentication: " + userAuthentication);
		}

		return userAuthentication;
	}

}
