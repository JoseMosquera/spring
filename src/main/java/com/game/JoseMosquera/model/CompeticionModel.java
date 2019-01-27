package com.game.JoseMosquera.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class CompeticionModel {

	@NotNull
	private int competicion_id;

	@NotNull
	@Size(min = 20,max = 100)
	private String nombre;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha;

	@NotNull
	@Size(min = 10,max = 100)
	private String lugar;

	@NotNull
	@Size(min = 500,max = 1000)
	private String descripcion;

	public int getCompeticion_id() {
		return competicion_id;
	}

	public void setCompeticion_id(int competicion_id) {
		this.competicion_id = competicion_id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public CompeticionModel(int competicion_id, String nombre, Date fecha, String lugar, String descripcion) {
		this.competicion_id = competicion_id;
		this.nombre = nombre;
		this.fecha = fecha;
		this.lugar = lugar;
		this.descripcion = descripcion;
	}

	public CompeticionModel() {}

	@Override
	public String toString() {
		return "CompeticionModel [competicion_id=" + competicion_id + ", nombre=" + nombre + ", fecha=" + fecha
				+ ", lugar=" + lugar + ", descripcion=" + descripcion + "]";
	}
	
}
