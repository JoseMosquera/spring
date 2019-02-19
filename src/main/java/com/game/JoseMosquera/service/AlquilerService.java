package com.game.JoseMosquera.service;

import java.util.List;

import com.game.JoseMosquera.model.AlquilerModel;

public interface AlquilerService {
	public abstract List<AlquilerModel> listAllAlquileresRealizados();
	public abstract List<AlquilerModel> listAllAlquileresPendientes();
	public abstract List<AlquilerModel> listAllAlquileresRealizadosAdmin();
	public abstract List<AlquilerModel> listAllAlquileresPendientesAdmin();
	public abstract AlquilerModel alquilarJuego(int idJuego);
	public abstract AlquilerModel devolverJuego(int idAlquiler);
	public abstract void removeAlquiler(int id);
}
