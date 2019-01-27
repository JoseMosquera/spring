package com.game.JoseMosquera.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class VentaModel {

	@NotNull
	private Date fehca;
	
	@NotNull
	private UserModel userModel;
	
	@NotNull
	private JuegoModel juegoModel;

	public Date getFehca() {
		return fehca;
	}

	public void setFehca(Date fehca) {
		this.fehca = fehca;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public JuegoModel getJuegoModel() {
		return juegoModel;
	}

	public void setJuegoModel(JuegoModel juegoModel) {
		this.juegoModel = juegoModel;
	}

	public VentaModel(Date fehca, UserModel userModel, JuegoModel juegoModel) {
		super();
		this.fehca = fehca;
		this.userModel = userModel;
		this.juegoModel = juegoModel;
	}

	public VentaModel() {
		super();
	}

	@Override
	public String toString() {
		return "VentaModel [fehca=" + fehca + ", userModel=" + userModel + ", juegoModel=" + juegoModel + "]";
	}
}
