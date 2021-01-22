package it.objectmethod.cce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.objectmethod.cce.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByEmailAndPassword(String email,String password);
	
	public User findByIdutente(int idutente);
	
}
