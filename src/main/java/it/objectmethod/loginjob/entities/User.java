package it.objectmethod.loginjob.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="utenti")
public class User {

	@Id
	private int idutente;
	
	@Column(name="name")
	private String name;
	
	@Column(name="role")
	private String role;
	
	@Column(name="password")
	private String password;
	
	@Column(name="email")
	private String email;
	
	public int getIdUtente() {
		return idutente;
	}
	public void setIdUtente(int idUtente) {
		this.idutente = idUtente;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
