package com.game.JoseMosquera.model;

import javax.validation.constraints.NotNull;

public class ParticipacionModel {

	@NotNull
	private int participacion_id;
	
	@NotNull
	private CompeticionModel competicionModel;
	
	@NotNull
	private UserModel userModel;
	
	@NotNull
	private int posicion;

	public int getParticipacion_id() {
		return participacion_id;
	}

	public void setParticipacion_id(int participacion_id) {
		this.participacion_id = participacion_id;
	}

	public CompeticionModel getCompeticionModel() {
		return competicionModel;
	}

	public void setCompeticionModel(CompeticionModel competicion_id) {
		this.competicionModel = competicion_id;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public ParticipacionModel(int participacion_id, CompeticionModel competicionModel, UserModel userModel, int posicion) {
		super();
		this.participacion_id = participacion_id;
		this.competicionModel = competicionModel;
		this.userModel = userModel;
		this.posicion = posicion;
	}

	public ParticipacionModel() {
		super();
	}

	@Override
	public String toString() {
		return "ParticipacionModel [participacion_id=" + participacion_id + ", competicion_id=" + competicionModel
				+ ", user_id=" + userModel + ", posicion=" + posicion + "]";
	}
}
