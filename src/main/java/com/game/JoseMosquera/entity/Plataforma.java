package com.game.JoseMosquera.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Plataforma {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "plataforma_id")
	private int plataforma_id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@ManyToMany(mappedBy = "plataformasList")
	private List<Juego> juegosPlataformasList;

	public int getPlataforma_id() {
		return plataforma_id;
	}

	public void setPlataforma_id(int plataforma_id) {
		this.plataforma_id = plataforma_id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Juego> getJuegosPlataformasList() {
		return juegosPlataformasList;
	}

	public void setJuegosPlataformasList(List<Juego> juegosPlataformasList) {
		this.juegosPlataformasList = juegosPlataformasList;
	}

	public Plataforma(int plataforma_id, String nombre, List<Juego> juegosPlataformasList) {
		super();
		this.plataforma_id = plataforma_id;
		this.nombre = nombre;
		this.juegosPlataformasList = juegosPlataformasList;
	}

	public Plataforma() {
		super();
	}

	@Override
	public String toString() {
		return "Plataforma [plataforma_id=" + plataforma_id + ", nombre=" + nombre + "]";
	}
}
