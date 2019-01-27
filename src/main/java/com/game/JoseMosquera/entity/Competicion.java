package com.game.JoseMosquera.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "competiciones")
public class Competicion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "competicion_id")
	private int competicion_id;
	
	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;
	
	@Column(name = "fecha", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha;
	
	@Column(name = "lugar", nullable = false, length = 100)
	private String lugar;
	
	@Column(name ="descripcion", nullable = false, length = 1000)
	private String descripcion;
	
	@OneToMany(mappedBy = "competicion", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Participacion> participaciones;

	public Set<Participacion> getParticipaciones() {
		return participaciones;
	}

	public void setParticipaciones(Set<Participacion> participaciones) {
		this.participaciones = participaciones;
	}

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

	public Competicion(int competicion_id, String nombre, Date fecha, String lugar, String descripcion) {
		this.competicion_id = competicion_id;
		this.nombre = nombre;
		this.fecha = fecha;
		this.lugar = lugar;
		this.descripcion = descripcion;
	}

	public Competicion() {}

	@Override
	public String toString() {
		return "Competicion [competicion_id=" + competicion_id + ", nombre=" + nombre + ", fecha=" + fecha + ", lugar="
				+ lugar + ", descripcion=" + descripcion + "]";
	}
}
