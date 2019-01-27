package com.game.JoseMosquera.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class AlquilerModel {

	@NotNull
	private Date fehca;
	
	@NotNull
	private UserModel userModel;
	
	@NotNull
	private JuegoModel juegoModel;
	
	@NotNull
	private boolean devuelto;

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

	public boolean isDevuelto() {
		return devuelto;
	}

	public void setDevuelto(boolean devuelto) {
		this.devuelto = devuelto;
	}

	public AlquilerModel(@NotNull Date fehca, @NotNull UserModel userModel, @NotNull JuegoModel juegoModel,
			@NotNull boolean devuelto) {
		super();
		this.fehca = fehca;
		this.userModel = userModel;
		this.juegoModel = juegoModel;
		this.devuelto = devuelto;
	}

	public AlquilerModel() {
		super();
	}

	@Override
	public String toString() {
		return "AlquilerModel [fehca=" + fehca + ", userModel=" + userModel + ", juegoModel=" + juegoModel
				+ ", devuelto=" + devuelto + "]";
	}
}
