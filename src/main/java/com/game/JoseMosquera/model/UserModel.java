package com.game.JoseMosquera.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserModel {

	@NotNull
	private int id;
	
	@NotNull
	@Size(min = 3, max = 20)
	@Pattern(regexp = "^[^0-9]{3,20}", message = "solo se permiten carácteres alfabéticos")
	private String nombre;
	
	@NotNull
	@Size(min = 3, max = 40)
	@Pattern(regexp = "^[^0-9]{3,20}", message = "solo se permiten carácteres alfabéticos")
	private String apellidos;
	
	@NotNull
	@Size(min = 3, max = 20)
	private String username;
	
	@NotNull
	@Size(min = 8, max = 15)
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])([A-Za-z\\d$@!%*?&]|[^ ]){8,15}$", message  ="la contraseña debe tener minusculas, mayusculas, un numero y alguno de los siguientes carácteres especiales ($, @, !, %, *, ?, &)")
	private String password;
	
	@NotNull
	private boolean enable;
	
	@NotNull
	@Pattern(regexp = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$", message = "correo electrónico invalido")
	private String email;
	
	@NotNull
	@Pattern(regexp = "^[0-9]{9}$", message = "el teléfono debe tener 9 carácteres digitales")
	private String telefono;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public UserModel(int id, String username, String password, boolean enable, String nombre, String apellidos,
			String email, String telefono) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.enable = enable;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.telefono = telefono;
	}

	public UserModel() {}

	@Override
	public String toString() {
		return "UserModel [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", username=" + username
				+ ", password=" + password + ", enable=" + enable + ", email=" + email + ", telefono=" + telefono + "]";
	}
	
}
