package com.game.JoseMosquera.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "alquileres", uniqueConstraints = @UniqueConstraint(
		columnNames= {"juego_id", "user_id", "fecha"}))
public class Alquiler implements Serializable{

	@Id
	@Column(name = "fecha")
	private Date fecha;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "juego_id")
	private Juego juego;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "devuelto")
	private boolean devuelto;

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

	public Alquiler(Date fecha, Juego juego, User user, boolean devuelto) {
		super();
		this.fecha = fecha;
		this.juego = juego;
		this.user = user;
		this.devuelto = devuelto;
	}

	public Alquiler() {
		super();
	}

	@Override
	public String toString() {
		return "Alquiler [fecha=" + fecha + ", juego=" + juego + ", user=" + user + ", devuelto=" + devuelto + "]";
	}
}
