package com.game.JoseMosquera.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	private int user_id;
	
	@Column(name = "username", unique = true, nullable = false, length = 20)
	private String username;
	
	@Column(name = "password", nullable = false, length = 60)
	private String password;
	
	@Column(name = "enable", nullable = false)
	private boolean enable;
	
	@Column(name = "nombre", nullable = false, length = 50)
	private String nombre;
	
	@Column(name = "apellidos", nullable = false, length = 50)
	private String apellidos;
	
	@Column(name = "email", nullable = false, length = 20)
	private String email;
	
	@Column(name = "telefono", nullable = false, length = 9)
	private String telefono;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private Set<Role> userRole = new HashSet<Role>();
	
	@OneToMany(mappedBy = "user")
	private Set<Participacion> participaciones;
	
	@OneToMany(mappedBy = "user")
	private Set<Venta> ventas;
	
	@OneToMany(mappedBy = "user")
	private Set<Alquiler> alquileres;

	public Set<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(Set<Venta> ventas) {
		this.ventas = ventas;
	}

	public Set<Alquiler> getAlquileres() {
		return alquileres;
	}

	public void setAlquileres(Set<Alquiler> alquileres) {
		this.alquileres = alquileres;
	}

	public Set<Participacion> getParticipaciones() {
		return participaciones;
	}

	public void setParticipaciones(Set<Participacion> participaciones) {
		this.participaciones = participaciones;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
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

	public Set<Role> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<Role> userRole) {
		this.userRole = userRole;
	}

	public User(int user_id, String username, String password, boolean enable, String nombre, String apellidos,
			String email, String telefono, Set<Participacion> participaciones, Set<Venta> ventas,
			Set<Alquiler> alquileres) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.enable = enable;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.telefono = telefono;
		this.participaciones = participaciones;
		this.ventas = ventas;
		this.alquileres = alquileres;
	}

	public User(int user_id, String username, String password, boolean enable, String nombre, String apellidos,
			String email, String telefono, Set<Role> userRole, Set<Participacion> participaciones, Set<Venta> ventas,
			Set<Alquiler> alquileres) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.enable = enable;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.telefono = telefono;
		this.userRole = userRole;
		this.participaciones = participaciones;
		this.ventas = ventas;
		this.alquileres = alquileres;
	}

	public User() {	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", username=" + username + ", password=" + password + ", enable=" + enable
				+ ", nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email + ", telefono=" + telefono
				+ ", userRole=" + userRole + "]";
	}
	
}