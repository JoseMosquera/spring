package com.game.JoseMosquera.model;

import java.util.Set;

import javax.validation.constraints.NotNull;

public class PlataformaModel {

	@NotNull
	private int plataforma_id;
	
	@NotNull
	private String nombre;
	
	private Set<JuegoModel> juegoList;

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

	public Set<JuegoModel> getJuegoList() {
		return juegoList;
	}

	public void setJuegoList(Set<JuegoModel> juegoList) {
		this.juegoList = juegoList;
	}

	public PlataformaModel(int plataforma_id, String nombre, Set<JuegoModel> juegoList) {
		super();
		this.plataforma_id = plataforma_id;
		this.nombre = nombre;
		this.juegoList = juegoList;
	}

	public PlataformaModel() {
		super();
	}

	@Override
	public String toString() {
		return "PlataformaModel [plataforma_id=" + plataforma_id + ", nombre=" + nombre + "]";
	}
}
