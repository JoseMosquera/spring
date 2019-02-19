package com.game.JoseMosquera.service;

import java.util.List;

import com.game.JoseMosquera.model.JuegoModel;

public interface JuegoService {

	public abstract List<JuegoModel> listJuegosVenta();
	public abstract List<JuegoModel> listJuegoAlquiler();
//	public abstract void addCategoriasJuego(int juego_id, int categoria_id);
	public abstract JuegoModel addJuego(JuegoModel juegoModel);
	public abstract void removeJuego(int id);
	public abstract JuegoModel juego(int id);
}
