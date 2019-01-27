package com.game.JoseMosquera.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "categoria_id")
	private int categoria_id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@ManyToMany(mappedBy = "categoriasList")
	private List<Juego> juegosList;

	public int getCategoria_id() {
		return categoria_id;
	}

	public void setCategoria_id(int categoria_id) {
		this.categoria_id = categoria_id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Juego> getJuegos() {
		return juegosList;
	}

	public void setJuegos(List<Juego> juegosList) {
		this.juegosList = juegosList;
	}

	public Categoria(int categoria_id, String nombre, List<Juego> juegosList) {
		super();
		this.categoria_id = categoria_id;
		this.nombre = nombre;
		this.juegosList = juegosList;
	}

	public Categoria() {
		super();
	}

	@Override
	public String toString() {
		return "Categoria [categoria_id=" + categoria_id + ", nombre=" + nombre + "]";
	}
}
