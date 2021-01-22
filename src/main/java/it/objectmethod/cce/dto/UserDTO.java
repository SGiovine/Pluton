package it.objectmethod.cce.dto;

public class UserDTO {

	private int idUtente;
	private String name;
	private String role;
	private String email;

	public boolean equals(UserDTO userdto) {
		boolean bool = false;
		
		if(userdto.getEmail().equals(this.getEmail())&&userdto.getIdUtente()==this.getIdUtente()&&userdto.getRole().equals(this.getRole())&&userdto.getName().equals(this.getName())) {
			bool = true;
		}
		return bool;
	}

	public int getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
