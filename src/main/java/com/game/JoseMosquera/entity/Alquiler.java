package com.game.JoseMosquera.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "alquileres", uniqueConstraints = @UniqueConstraint(
		columnNames= {"juego_id", "user_id", "fecha"}))
public class Alquiler{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "alquiler_id")
	private int alquiler_id;
	
	
	@Column(name = "fecha")
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name = "juego_id")
	private Juego juego;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "devuelto")
	private boolean devuelto;

	public int getAlquiler_id() {
		return alquiler_id;
	}

	public void setAlquiler_id(int alquiler_id) {
		this.alquiler_id = alquiler_id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Juego getJuego() {
		return juego;
	}

	public void setJuego(Juego juego) {
		this.juego = juego;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isDevuelto() {
		return devuelto;
	}

	public void setDevuelto(boolean devuelto) {
		this.devuelto = devuelto;
	}

	public Alquiler() {
		super();
	}

	public Alquiler(int alquiler_id, Date fecha, Juego juego, User user, boolean devuelto) {
		super();
		this.alquiler_id = alquiler_id;
		this.fecha = fecha;
		this.juego = juego;
		this.user = user;
		this.devuelto = devuelto;
	}
}
