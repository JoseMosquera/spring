package com.game.JoseMosquera.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class AlquilerModel {
	
	@NotNull
	private int alquiler_id;

	@NotNull
	private Date fecha;
	
	@NotNull
	private UserModel userModel;
	
	@NotNull
	private JuegoModel juegoModel;
	
	@NotNull
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

	public AlquilerModel(int alquiler_id, Date fecha, UserModel userModel, JuegoModel juegoModel, boolean devuelto) {
		super();
		this.alquiler_id = alquiler_id;
		this.fecha = fecha;
		this.userModel = userModel;
		this.juegoModel = juegoModel;
		this.devuelto = devuelto;
	}

	public AlquilerModel() {
		super();
	}

	@Override
	public String toString() {
		return "AlquilerModel [alquiler_id=" + alquiler_id + ", fecha=" + fecha + ", userModel=" + userModel
				+ ", juegoModel=" + juegoModel + ", devuelto=" + devuelto + "]";
	}
}
