package it.objectmethod.loginjob.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.objectmethod.loginjob.dto.UserDTO;
import it.objectmethod.loginjob.entities.User;
import it.objectmethod.loginjob.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRep;

	public UserDTO loginCheck(String emailToCheck, String passwordToCheck) {

		List<User> users = userRep.findAll();

		UserDTO userToResp = null;

		for (User user : users) {

			if (user.getEmail().equals(emailToCheck)) {
				System.out.println("email true");
				if (user.getPassword().equals(passwordToCheck)) {
					System.out.println("password true");
					userToResp = new UserDTO();
					userToResp.setEmail(user.getEmail());
					userToResp.setIdUtente(user.getIdUtente());
					userToResp.setName(user.getName());
					userToResp.setRole(user.getRole());

					break;
				}
			}

		}

		return userToResp;

	}

}
