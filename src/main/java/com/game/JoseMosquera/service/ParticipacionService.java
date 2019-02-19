package com.game.JoseMosquera.service;

import java.util.List;

import com.game.JoseMosquera.model.ParticipacionModel;

public interface ParticipacionService {

	public abstract List<ParticipacionModel> listAllParticipaciones();
	public abstract List<ParticipacionModel> listAllCompeticionesPendientes();
	public abstract List<ParticipacionModel> listAllCompeticionesPendientesAdmin();
	public abstract List<ParticipacionModel> listAllCompeticionesRealizadas();
	public abstract ParticipacionModel addParticipacion(int idCompeticion);
	public abstract ParticipacionModel asignarPosicion(int posicion, int id);
	public abstract void removeParticipacion(int id);
	public abstract ParticipacionModel participacion(int id);
}
