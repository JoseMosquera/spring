package com.game.JoseMosquera.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class VentaModel {
	
	@NotNull
	private int venta_id;

	@NotNull
	private Date fecha;
	
	@NotNull
	private UserModel userModel;
	
	@NotNull
	private JuegoModel juegoModel;

	public int getVenta_id() {
		return venta_id;
	}

	public void setVenta_id(int venta_id) {
		this.venta_id = venta_id;
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

	public VentaModel(int venta_id, Date fecha, UserModel userModel, JuegoModel juegoModel) {
		super();
		this.venta_id = venta_id;
		this.fecha = fecha;
		this.userModel = userModel;
		this.juegoModel = juegoModel;
	}

	public VentaModel() {
		super();
	}

	@Override
	public String toString() {
		return "VentaModel [venta_id=" + venta_id + ", fecha=" + fecha + ", userModel=" + userModel + ", juegoModel="
				+ juegoModel + "]";
	}
}
