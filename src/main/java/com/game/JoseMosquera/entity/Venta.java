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
@Table(name = "ventas", uniqueConstraints = @UniqueConstraint(
		columnNames= {"juego_id", "user_id", "fecha"}))
public class Venta{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "venta_id")
	private int venta_id;
	
	@Column(name = "fecha")
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name = "juego_id")
	private Juego juego;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

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

	public Venta(Date fecha, Juego juego, User user) {
		super();
		this.fecha = fecha;
		this.juego = juego;
		this.user = user;
	}
	
	public Venta() {
		super();
	}

	@Override
	public String toString() {
		return "Venta [fecha=" + fecha + ", juego=" + juego + ", user=" + user + "]";
	}
}
