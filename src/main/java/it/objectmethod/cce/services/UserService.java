package it.objectmethod.cce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.objectmethod.cce.dto.UserDTO;
import it.objectmethod.cce.entities.User;
import it.objectmethod.cce.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRep;

	public UserDTO loginCheck(String emailToCheck, String passwordToCheck) {

		User user = userRep.findByEmailAndPassword(emailToCheck, passwordToCheck);

		UserDTO userDTO = null;

		if (user != null) {
			userDTO = new UserDTO();
			userDTO.setEmail(user.getEmail());
			userDTO.setIdUtente(user.getIdUtente());
			userDTO.setName(user.getName());
			userDTO.setRole(user.getRole());
		}

		return userDTO;

	}

}
