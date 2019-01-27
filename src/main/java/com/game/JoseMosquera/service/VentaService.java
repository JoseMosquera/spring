package com.game.JoseMosquera.service;

import java.util.List;

import com.game.JoseMosquera.model.VentaModel;

public interface VentaService {

	public abstract List<VentaModel> listAllVentas();
	public abstract VentaModel addVenta(int idJuego);
	public abstract void removeVenta(int id);
}
