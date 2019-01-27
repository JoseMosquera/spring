package com.game.JoseMosquera.repository;

import java.util.List;

import com.game.JoseMosquera.model.AlquilerModel;

public interface AlquilerService {

	public abstract List<AlquilerModel> listAllAlquileres();
	public abstract AlquilerModel alquilarJuego(int idJuego);
	public abstract AlquilerModel devolverJuego(int idJuego);
	public abstract void removeAlquiler(int id);
}
