package it.objectmethod.loginjob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.objectmethod.loginjob.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	
	
}
