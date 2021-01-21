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

		UserDTO userToResp = null;

		if (user != null) {

			userToResp = new UserDTO();
			userToResp.setEmail(user.getEmail());
			userToResp.setIdUtente(user.getIdUtente());
			userToResp.setName(user.getName());
			userToResp.setRole(user.getRole());

		}

		return userToResp;

	}

}
