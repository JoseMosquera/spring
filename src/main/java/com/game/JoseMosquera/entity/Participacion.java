package com.game.JoseMosquera.entity;

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
@Table(name = "participaciones", uniqueConstraints = @UniqueConstraint(
		columnNames= {"competicion_id", "user_id"}))
public class Participacion{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "participacion_id")
	private int participacion_id;
	
	@ManyToOne
	@JoinColumn(name = "competicion_id")
	private Competicion competicion;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "posicion", nullable = true)
	private int posicion;

	public int getParticipacion_id() {
		return participacion_id;
	}

	public void setParticipacion_id(int participacion_id) {
		this.participacion_id = participacion_id;
	}

	public Competicion getCompeticion() {
		return competicion;
	}

	public void setCompeticion(Competicion competicion) {
		this.competicion = competicion;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int podicion) {
		this.posicion = podicion;
	}

	public Participacion(int participacion_id, Competicion competicion, User user, int posicion) {
		this.participacion_id = participacion_id;
		this.competicion = competicion;
		this.user = user;
		this.posicion = posicion;
	}

	public Participacion() {}

	@Override
	public String toString() {
		return "Participacion [participacion_id=" + participacion_id + ", competicion=" + competicion + ", user=" + user
				+ ", posicion=" + posicion + "]";
	}
}
