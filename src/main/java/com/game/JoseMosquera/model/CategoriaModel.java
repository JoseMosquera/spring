package com.game.JoseMosquera.model;

import java.util.Set;

import javax.validation.constraints.NotNull;

public class CategoriaModel {

	@NotNull
	private int categoria_id;
	
	@NotNull
	private String nombre;
	
	private Set<JuegoModel> juegosList;

	public Set<JuegoModel> getJuegos() {
		return juegosList;
	}

	public void setJuegos(Set<JuegoModel> juegosList) {
		this.juegosList = juegosList;
	}

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

	public CategoriaModel() {
		super();
	}

	public CategoriaModel(int categoria_id, String nombre, Set<JuegoModel> juegosList) {
		super();
		this.categoria_id = categoria_id;
		this.nombre = nombre;
		this.juegosList = juegosList;
	}

	@Override
	public String toString() {
		return "CategoriaModel [categoria_id=" + categoria_id + ", nombre=" + nombre + "]";
	}
}
